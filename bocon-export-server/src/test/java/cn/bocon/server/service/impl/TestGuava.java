package cn.bocon.server.service.impl;

import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.List;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;
import com.google.common.io.InputSupplier;

public class TestGuava {

	@Test
	public void test() throws Exception {
		File dest = new File("d:\\destfile.txt");
		File source = new File("d:\\srcfile.txt");
		ByteSource byteSource = Files.asByteSource(source);
		ByteSink byteSink = Files.asByteSink(dest);
		byteSource.copyTo(byteSink);
	}
	
}
