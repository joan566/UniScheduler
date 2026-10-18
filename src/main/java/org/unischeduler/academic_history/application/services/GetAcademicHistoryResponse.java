package org.unischeduler.academic_history.application.services;

import java.util.List;

public record GetAcademicHistoryResponse(boolean successfully, String message, String programName, int approvedCredits,
                                         double average, List<AcademicHistoryInfo> academicHistory) {
}
