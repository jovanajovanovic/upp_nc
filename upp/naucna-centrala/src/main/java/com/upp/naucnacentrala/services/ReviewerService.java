package com.upp.naucnacentrala.services;

import com.upp.naucnacentrala.dto.EditorReviewerDto;
import com.upp.naucnacentrala.exceptions.ObjectNotFound;

import java.util.List;

public interface ReviewerService {

    List<EditorReviewerDto> getReviewers(List<String> scientifics);

    List<String> getReviewersByCode(Object value) throws ObjectNotFound;

}
