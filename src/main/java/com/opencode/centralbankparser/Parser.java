package com.opencode.centralbankparser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencode.centralbankparser.data.entities.*;
import com.opencode.centralbankparser.data.services.*;
import com.opencode.centralbankparser.references.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Service
public class Parser {
    @Autowired
    private AccountsService accountsService;
    @Autowired
    private AccRstrListService accRstrListService;
    @Autowired
    private BicDirectoryEntryService bicDirectoryEntryService;
    @Autowired
    private Ed807Service ed807Service;
    @Autowired
    private InitialEdService initialEdService;
    @Autowired
    private ParticipantInfoService participantInfoService;
    @Autowired
    private PartInfoService partInfoService;
    @Autowired
    private RstrListService rstrListService;
    @Autowired
    private SwbicsService swbicsService;

    @Autowired
    private CreationReasonService creationReasonService;
    @Autowired
    private InfoTypeCodeService infoTypeCodeService;
    @Autowired
    private ChangeTypeService changeTypeService;
    @Autowired
    private PtTypeService ptTypeService;
    @Autowired
    private SrvcsService srvcsService;
    @Autowired
    private XchTypeService xchTypeService;
    @Autowired
    private ParticipantStatusService participantStatusService;
    @Autowired
    private RstrService rstrService;
    @Autowired
    private RegulationAccountTypeService regulationAccountTypeService;
    @Autowired
    private AccountStatusService accountStatusService;
    @Autowired
    private AccRstrService accRstrService;
    public void deserializeXml() {
        try {
            File file = new File("test-data.xml");
            XmlMapper xmlMapper = new XmlMapper();
            JsonNode root = xmlMapper.readTree(file);
            saveEd807(root);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deserializeXml(MultipartFile file) throws IOException, ParseException {
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode root = xmlMapper.readTree(file.getBytes());
        saveEd807(root);
    }

    private void saveEd807(JsonNode node) throws ParseException, UnsupportedEncodingException {
        Ed807Entity entity = new Ed807Entity();
        entity.setEdNo(node.findValue("EDNo").asText());

        SimpleDateFormat edDate = new SimpleDateFormat("yyyy-MM-dd");
        Date parseEdDate = edDate.parse(node.findValue("EDDate").asText());
        entity.setEdDate(new Timestamp(parseEdDate.getTime()));

        entity.setEdAuthor(node.findValue("EDAuthor").asText());

        if (node.findValue("EDReceiver") != null)
            entity.setEdReceiver(node.findValue("EDReceiver").asText(null));

        if (creationReasonService.findByCode(node.findValue("CreationReason").asText()).isPresent())
            entity.setCreationReasonEntity(
                    creationReasonService.findByCode(node.findValue("CreationReason").asText()).get());

        SimpleDateFormat creationDateTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date parsedCreationDateTime = creationDateTime.parse(node.findValue("CreationDateTime").asText());
        entity.setBusinessDay(new Timestamp(parsedCreationDateTime.getTime()));

        if (infoTypeCodeService.findByCode(node.findValue("InfoTypeCode").asText()).isPresent())
            entity.setInfoTypeCodeEntity(
                    infoTypeCodeService.findByCode(node.findValue("InfoTypeCode").asText()).get());

        SimpleDateFormat businessDay = new SimpleDateFormat("yyyy-MM-dd");
        Date parseBusinessDay = businessDay.parse(node.findValue("BusinessDay").asText());
        entity.setCreationDateTime(new Timestamp(parseBusinessDay.getTime()));

        if (node.get("PartInfo") != null)
            savePartInfo(entity, node.get("PartInfo"));

        if (node.get("InitialED") != null)
            saveInitialEd(entity, node.get("InitialED"));

        ed807Service.save(entity);
        saveBicDirectories(entity, node.get("BICDirectoryEntry"));
    }

    private void savePartInfo(Ed807Entity ed807, JsonNode node){
        PartInfoEntity partInfo = new PartInfoEntity();
        partInfo.setPartNo(node.findValue("PartNo").asText());
        partInfo.setPartQuantity(node.findValue("PartQuantity").asText());
        partInfo.setPartAggregateId(node.findValue("PartAggregateId").asText());

        ed807.setPartInfoEntity(partInfo);
        partInfoService.save(partInfo);
    }

    private void saveInitialEd(Ed807Entity ed807, JsonNode node) throws ParseException {
        InitialEdEntity initialEd = new InitialEdEntity();
        initialEd.setEdNo(node.findValue("EDNo").asText());

        SimpleDateFormat edDate = new SimpleDateFormat("yyyy-MM-dd");
        Date parseEdDate = edDate.parse(node.findValue("EDDate").asText());
        initialEd.setEdDate(new Timestamp(parseEdDate.getTime()));

        initialEd.setEdAuthor(node.findValue("EDAuthor").asText());

        ed807.setInitialEdEntity(initialEd);
        initialEdService.save(initialEd);
    }

    private void saveBicDirectories(Ed807Entity ed807, JsonNode node) throws ParseException, UnsupportedEncodingException {
        for (final JsonNode bic : node) {
            saveBicDirectory(ed807, bic);
        }
    }

    private void saveBicDirectory(Ed807Entity ed807, JsonNode node) throws ParseException, UnsupportedEncodingException {
        BicDirectoryEntryEntity entity = new BicDirectoryEntryEntity();
        entity.setBic(node.findValue("BIC").asText());

        if (node.findValue("ChangeType") != null &&
                changeTypeService.findByCode(node.findValue("ChangeType").asText()).isPresent())
            entity.setChangeType(changeTypeService.findByCode(node.findValue("ChangeType").asText()).get());

        entity.setEd807(ed807);
        bicDirectoryEntryService.save(entity);

        saveParticipantInfo(entity, node.get("ParticipantInfo"));

        if (node.get("SWBICS") != null && node.get("SWBICS").isArray())
            saveSwbicsList(entity, node.get("SWBICS"));
        else if (node.get("SWBICS") != null) {
            saveSwbic(entity, node.get("SWBICS"));
        }

        if (node.get("Accounts") != null && node.get("Accounts").isArray())
            saveAccounts(entity, node.get("Accounts"));
        else if (node.get("Accounts") != null) {
            saveAccount(entity, node.get("Accounts"));
        }
    }

    private void saveParticipantInfo(BicDirectoryEntryEntity bicDirectoryEntry, JsonNode node) throws ParseException, UnsupportedEncodingException {
        ParticipantInfoEntity entity = new ParticipantInfoEntity();

        byte[] bytes = node.findValue("NameP").asText().getBytes("Windows-1251");
        String value = new String(bytes, Charset.forName("cp1251"));
        entity.setNameP(value);

        if (node.findValue("EngName") != null)
            entity.setEngName(node.findValue("EngName").asText());
        if (node.findValue("RegN") != null) {
            bytes = node.findValue("RegN").asText().getBytes("Windows-1251");
            value = new String(bytes, Charset.forName("cp1251"));
            entity.setRegN(value);
        }
        if (node.findValue("CntrCd") != null)
            entity.setCntrCd(node.findValue("CntrCd").asText());

        entity.setRgn(node.findValue("Rgn").asText());

        if (node.findValue("Ind") != null)
            entity.setInd(node.findValue("Ind").asText());
        if (node.findValue("Tnp") != null) {
            bytes = node.findValue("Tnp").asText().getBytes("Windows-1251");
            value = new String(bytes, Charset.forName("cp1251"));
            entity.setTnp(value);
        }
        if (node.findValue("Nnp") != null) {
            bytes = node.findValue("Nnp").asText().getBytes("Windows-1251");
            value = new String(bytes, Charset.forName("cp1251"));
            entity.setNnp(value);
        }
        if (node.findValue("Adr") != null){
            bytes = node.findValue("Adr").asText().getBytes("Windows-1251");
            value = new String(bytes, Charset.forName("cp1251"));
            entity.setAdr(value);
        }

        if (node.findValue("PrntBIC") != null)
            entity.setPrntBic(node.findValue("PrntBIC").asText());

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = date.parse(node.findValue("DateIn").asText());
        entity.setDateIn(new Timestamp(parsedDate.getTime()));

        if (node.findValue("DateOut") != null){
            parsedDate = date.parse(node.findValue("DateOut").asText());
            entity.setDateOut(new Timestamp(parsedDate.getTime()));
        }
        if (ptTypeService.findByCode(node.findValue("PtType").asText()).isPresent())
            entity.setPtType(ptTypeService.findByCode(node.findValue("PtType").asText()).get());
        if (srvcsService.findByCode(node.findValue("Srvcs").asText()).isPresent())
            entity.setSrvcs(srvcsService.findByCode(node.findValue("Srvcs").asText()).get());
        if (xchTypeService.findByCode(node.findValue("XchType").asText()).isPresent())
            entity.setXchType(xchTypeService.findByCode(node.findValue("XchType").asText()).get());

        entity.setUid(node.findValue("UID").asText());

        if (node.findValue("ParticipantStatus") != null &&
                participantStatusService.findByCode(node.findValue("ParticipantStatus").asText()).isPresent())
            entity.setParticipantStatus(
                    participantStatusService.findByCode(node.findValue("ParticipantStatus").asText()).get());

        entity.setBicDirectoryEntryEntity(bicDirectoryEntry);
        participantInfoService.save(entity);

        if (node.get("RstrList") != null && node.get("RstrList").isArray())
            saveRstrLists(entity, node.get("RstrList"));
        else if (node.get("RstrList") != null) {
            saveRstrList(entity, node.get("RstrList"));
        }

    }

    private void saveRstrLists(ParticipantInfoEntity participantInfo, JsonNode node) throws ParseException {
        for (final JsonNode rstrList : node) {
            saveRstrList(participantInfo, rstrList);
        }
    }

    private void saveRstrList(ParticipantInfoEntity participantInfo, JsonNode node) throws ParseException {
        RstrListEntity entity = new RstrListEntity();

        if (rstrService.findByCode(node.findValue("Rstr").asText()).isPresent())
            entity.setRstr(rstrService.findByCode(node.findValue("Rstr").asText()).get());

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = date.parse(node.findValue("RstrDate").asText());
        entity.setRstrDate(new Timestamp(parsedDate.getTime()));
        entity.setParticipantInfoEntity(participantInfo);

        rstrListService.save(entity);
    }

    private void saveSwbicsList(BicDirectoryEntryEntity bicDirectoryEntry, JsonNode node){
        for (final JsonNode swbics : node) {
            saveSwbic(bicDirectoryEntry, swbics);
        }
    }

    private void saveSwbic(BicDirectoryEntryEntity bicDirectoryEntry, JsonNode node){
        SwbicsEntity entity = new SwbicsEntity();

        entity.setSwbics(node.findValue("SWBIC").asText());
        entity.setDefaultSwbic(Objects.equals(node.findValue("DefaultSWBIC").asText(), "1"));
        entity.setBicDirectoryEntryEntity(bicDirectoryEntry);
        swbicsService.save(entity);
    }

    private void saveAccounts(BicDirectoryEntryEntity bicDirectoryEntry, JsonNode node) throws ParseException {
        for (final JsonNode account : node) {
            saveAccount(bicDirectoryEntry, account);
        }
    }

    private void saveAccount(BicDirectoryEntryEntity bicDirectoryEntry, JsonNode node) throws ParseException {
        AccountsEntity entity = new AccountsEntity();

        entity.setAccount(node.findValue("Account").asText());
        if (regulationAccountTypeService.findByCode(node.findValue("RegulationAccountType").asText()).isPresent())
            entity.setRegulationAccountType(regulationAccountTypeService.findByCode(
                    node.findValue("RegulationAccountType").asText()).get());

        if (node.findValue("CK") != null)
            entity.setCk(node.findValue("CK").asText());
        entity.setAccountCbrbic(node.findValue("AccountCBRBIC").asText());

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = date.parse(node.findValue("DateIn").asText());
        entity.setDateIn(new Timestamp(parsedDate.getTime()));

        if (node.findValue("DateOut") != null){
            parsedDate = date.parse(node.findValue("DateOut").asText());
            entity.setDateOut(new Timestamp(parsedDate.getTime()));
        }

        if (accountStatusService.findByCode(node.findValue("AccountStatus").asText()).isPresent())
            entity.setAccountStatus(accountStatusService.findByCode(node.findValue("AccountStatus").asText()).get());

        entity.setBicDirectoryEntry(bicDirectoryEntry);
        accountsService.save(entity);
        if (node.get("AccRstrList") != null && node.get("AccRstrList").isArray())
            saveAccRstrLists(entity, node.get("AccRstrList"));
        else if (node.get("Accounts") != null) {
            saveAccRstrList(entity, node.get("AccRstrList"));
        }
    }

    private void saveAccRstrLists(AccountsEntity accounts, JsonNode node) throws ParseException {
        for (final JsonNode accRstr : node) {
            saveAccRstrList(accounts, accRstr);
        }
    }

    private void saveAccRstrList(AccountsEntity accounts, JsonNode node) throws ParseException {
        AccRstrListEntity entity = new AccRstrListEntity();

        if (accRstrService.findByCode(node.findValue("AccRstr").asText()).isPresent())
            entity.setAccRstr(accRstrService.findByCode(node.findValue("AccRstr").asText()).get());

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = date.parse(node.findValue("AccRstrDate").asText());
        entity.setAccRstrDate(new Timestamp(parsedDate.getTime()));

        if (node.findValue("SuccessorBIC") != null)
            entity.setSuccessorBic(node.findValue("SuccessorBIC").asText());
        entity.setAccounts(accounts);

        accRstrListService.save(entity);
    }
}

