package com.connectglobe.service;

import com.connectglobe.model.postreport;
import com.connectglobe.web.dto.PostReportDto;


public interface postreportService extends postDetailsService{
	postreport save(PostReportDto postreportDto);
}
