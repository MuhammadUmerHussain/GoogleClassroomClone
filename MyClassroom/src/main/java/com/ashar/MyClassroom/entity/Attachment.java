package com.ashar.MyClassroom.entity;

import java.util.Arrays;

public class Attachment {

	    private String fileName;
	    private String fileType;

	    private byte[] data;

	    @Override
		public String toString() {
			return "Attachment [fileName=" + fileName + ", fileType=" + fileType + ", data=" + Arrays.toString(data)
					+ "]";
		}

		public Attachment(String fileName, String fileType, byte[] data) {
	        this.fileName = fileName;
	        this.fileType = fileType;
	        this.data = data;
	    }

	    public Attachment() {
	    }

	    
		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getFileType() {
			return fileType;
		}

		public void setFileType(String fileType) {
			this.fileType = fileType;
		}

		public byte[] getData() {
			return data;
		}

		public void setData(byte[] data) {
			this.data = data;
		}
}
