package com.upp.naucnacentrala.services;


import com.upp.naucnacentrala.dto.MagazineDto;
import com.upp.naucnacentrala.dto.MagazineRegisterDto;
import com.upp.naucnacentrala.exceptions.ObjectNotFound;
import com.upp.naucnacentrala.model.Magazine;

import java.util.List;

public interface MagazineService {
    List<Magazine> getAllMagazines();

    MagazineDto makeMagazineDto(MagazineRegisterDto dto);

    Magazine getMagazine(String issn) throws ObjectNotFound;
}
