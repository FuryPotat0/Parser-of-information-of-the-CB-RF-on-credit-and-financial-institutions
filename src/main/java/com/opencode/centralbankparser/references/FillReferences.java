package com.opencode.centralbankparser.references;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencode.centralbankparser.references.entities.*;
import com.opencode.centralbankparser.references.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Component
public class FillReferences {
    @Autowired
    private InfoTypeCodeService infoTypeCodeService;
    @Autowired
    private CreationReasonService creationReasonService;
    @Autowired
    private ChangeTypeService changeTypeService;
    @Autowired
    private RegulationAccountTypeService regulationAccountTypeService;
    @Autowired
    private AccountStatusService accountStatusService;
    @Autowired
    private AccRstrService accRstrService;
    @Autowired
    private SrvcsService srvcsService;
    @Autowired
    private ParticipantStatusService participantStatusService;
    @Autowired
    private RstrService rstrService;
    @Autowired
    private XchTypeService xchTypeService;
    @Autowired
    private PtTypeService ptTypeService;

    @EventListener(ApplicationReadyEvent.class)
    public void fillReferences() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        fillInfoTypeCode(objectMapper);
        fillCreationReason(objectMapper);
        fillChangeType(objectMapper);
        fillRegulationAccountType(objectMapper);
        fillAccountStatus(objectMapper);
        fillAccRstr(objectMapper);
        fillSrvcs(objectMapper);
        fillParticipantStatus(objectMapper);
        fillRstr(objectMapper);
        fillXchType(objectMapper);
        fillPtType(objectMapper);
    }

    private void fillInfoTypeCode(ObjectMapper objectMapper) throws IOException {
        List<InfoTypeCodeEntity> list = objectMapper.readValue(
                new URL("file:src/main/resources/static/referencesData/info_type_code.json"), new TypeReference<>() {});
        for (InfoTypeCodeEntity entity: list){
            infoTypeCodeService.save(entity);
        }
    }

    private void fillCreationReason(ObjectMapper objectMapper) throws IOException {
        List<CreationReasonEntity> list = objectMapper.readValue(
                new URL("file:src/main/resources/static/referencesData/creation_reason.json"), new TypeReference<>() {});
        for (CreationReasonEntity entity: list){
            creationReasonService.save(entity);
        }
    }

    private void fillChangeType(ObjectMapper objectMapper) throws IOException {
        List<ChangeTypeEntity> list = objectMapper.readValue(
                new URL("file:src/main/resources/static/referencesData/change_type.json"), new TypeReference<>() {});
        for (ChangeTypeEntity entity: list){
            changeTypeService.save(entity);
        }
    }

    private void fillRegulationAccountType(ObjectMapper objectMapper) throws IOException {
        List<RegulationAccountTypeEntity> list = objectMapper.readValue(
                new URL("file:src/main/resources/static/referencesData/regulation_account_type.json"), new TypeReference<>() {});
        for (RegulationAccountTypeEntity entity: list){
            regulationAccountTypeService.save(entity);
        }
    }

    private void fillAccountStatus(ObjectMapper objectMapper) throws IOException {
        List<AccountStatusEntity> list = objectMapper.readValue(
                new URL("file:src/main/resources/static/referencesData/account_status.json"), new TypeReference<>() {});
        for (AccountStatusEntity entity: list){
            accountStatusService.save(entity);
        }
    }

    private void fillAccRstr(ObjectMapper objectMapper) throws IOException {
        List<AccRstrEntity> list = objectMapper.readValue(
                new URL("file:src/main/resources/static/referencesData/acc_rstr.json"), new TypeReference<>() {});
        for (AccRstrEntity entity: list){
            accRstrService.save(entity);
        }
    }

    private void fillSrvcs(ObjectMapper objectMapper) throws IOException {
        List<SrvcsEntity> list = objectMapper.readValue(
                new URL("file:src/main/resources/static/referencesData/srvcs.json"), new TypeReference<>() {});
        for (SrvcsEntity entity: list){
            srvcsService.save(entity);
        }
    }

    private void fillParticipantStatus(ObjectMapper objectMapper) throws IOException {
        List<ParticipantStatusEntity> list = objectMapper.readValue(
                new URL("file:src/main/resources/static/referencesData/participant_status.json"), new TypeReference<>() {});
        for (ParticipantStatusEntity entity: list){
            participantStatusService.save(entity);
        }
    }

    private void fillRstr(ObjectMapper objectMapper) throws IOException {
        List<RstrEntity> list = objectMapper.readValue(
                new URL("file:src/main/resources/static/referencesData/rstr.json"), new TypeReference<>() {});
        for (RstrEntity entity: list){
            rstrService.save(entity);
        }
    }

    private void fillXchType(ObjectMapper objectMapper) throws IOException {
        List<XchTypeEntity> list = objectMapper.readValue(
                new URL("file:src/main/resources/static/referencesData/xch_type.json"), new TypeReference<>() {});
        for (XchTypeEntity entity: list){
            xchTypeService.save(entity);
        }
    }

    private void fillPtType(ObjectMapper objectMapper) throws IOException {
        List<PtTypeEntity> list = objectMapper.readValue(
                new URL("file:src/main/resources/static/referencesData/pt_type.json"), new TypeReference<>() {});
        for (PtTypeEntity entity: list){
            ptTypeService.save(entity);
        }
    }
}

