package com.upp.naucnacentrala.services;

import com.upp.naucnacentrala.dto.EditorReviewerDto;

import java.util.List;

public interface ReviewerService {

    List<EditorReviewerDto> getReviewers(List<String> scientifics);
}
