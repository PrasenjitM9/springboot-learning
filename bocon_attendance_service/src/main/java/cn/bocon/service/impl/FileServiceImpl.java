package cn.bocon.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.bocon.entity.CardData;
import cn.bocon.service.ICardDataService;
import cn.bocon.service.IFileService;
import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReader;

@Service
public class FileServiceImpl implements IFileService {
	@Autowired
	private ICardDataService cardDataService;

	@Override
	@Transactional
	public void handleData(File file) {
		InputStream inputXML = null;
		XLSReader mainReader = null;
		InputStream inputXLS = null;
		try {
			inputXML = new BufferedInputStream(getClass().getResourceAsStream("/templates/CardData.xml"));
			mainReader = ReaderBuilder.buildFromXML(inputXML);
			inputXLS = new BufferedInputStream(new FileInputStream(file));
			List<CardData> datas = Lists.newArrayList();
			Map<String, Object> beans = Maps.newHashMap();
			beans.put("datas", datas);
			mainReader.read(inputXLS, beans);
			System.out.println(beans);
			cardDataService.delete(null);
			cardDataService.insertBatch(datas);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
