package io.choerodon.test.manager.domain.service;

import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.ResponseEntity;

import io.choerodon.agile.api.dto.IssueDTO;
import io.choerodon.agile.api.dto.IssueTypeDTO;
import io.choerodon.agile.api.dto.PriorityDTO;
import io.choerodon.test.manager.domain.test.manager.entity.TestFileLoadHistoryE;
import io.choerodon.test.manager.domain.test.manager.entity.TestIssueFolderE;

public interface IExcelImportService {

    Workbook buildImportTemp();

    boolean cancelFileUpload(Long historyId);

    void processRow(IssueDTO issueDTO, Row row, List<Integer> errorRowIndexes);

    TestFileLoadHistoryE initLoadHistory(Long projectId, Long folderId, Long userId);

    String uploadErrorWorkbook(Workbook errorWorkbook, TestFileLoadHistoryE loadHistoryE);

    void shiftErrorRowsToTop(Sheet sheet, List<Integer> errorRowIndexes);

    TestIssueFolderE getFolder(Long projectId, Long versionId);

    boolean isCanceled(Long id);

    boolean isIssueHeaderRow(Row row);

    Long getIssueTypeId(Long organizationId, Long projectId, String applyType);

    Long getPriorityId(Long organizationId, Long projectId);

    IssueDTO processIssueHeaderRow(Row row, Long organizationId, Long projectId, Long versionId, Long folderId);

    void removeRow(Row row);

    void updateProgress(TestFileLoadHistoryE loadHistoryE, Long userId, double rate);

    void finishImport(TestFileLoadHistoryE loadHistoryE, Long userId, TestFileLoadHistoryE.Status status);

    boolean isEmptyTemp(Sheet sheet);

    Iterator<Row> rowIteratorSkipFirst(Sheet sheet);
}
