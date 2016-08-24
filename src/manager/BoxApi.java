package manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.box.sdk.*;

public class BoxApi {

	private static BoxApi instance = null;
	public static final String ACCESS_TOKEN = "1t87X7JWLJ7XVT94mno7F0157n7lsBc3";
	private BoxAPIConnection api;

	private BoxApi() {
		api = new BoxAPIConnection(ACCESS_TOKEN);
	}

	public static BoxApi getInstance() {
		if (instance == null) {
			instance = new BoxApi();
		}
		return instance;
	}

	public void uploadFile(BoxFolder folder, String file) throws IOException {
		FileInputStream stream = new FileInputStream(file);
		folder.uploadFile(stream, file);
		stream.close();
	}

	public File downloadFile(String fileId, String filePath) throws IOException {
		BoxFile file = new BoxFile(api, fileId);
		BoxFile.Info info = file.getInfo();

		File f = new File(filePath);
		f.getParentFile().mkdirs();
		FileWriter writer = new FileWriter(f);
		FileOutputStream stream = new FileOutputStream(f);
		file.download(stream);
		stream.close();
		return f;
	}

	public void deleteFile(String fileId) {
		BoxFile file = new BoxFile(api, fileId);
		file.delete();
	}

	public BoxFolder getBoxFolder(String folderId) {
		return new BoxFolder(api, folderId);
	}

	public BoxFolder getRootFolder() {
		return BoxFolder.getRootFolder(api);
	}

	public List<BoxItem.Info> getFolderItems(BoxFolder folder) {
		List<BoxItem.Info> folderItems = new ArrayList<BoxItem.Info>();
		for (BoxItem.Info itemInfo : folder) {
			folderItems.add(itemInfo);
		}
		return folderItems;
	}

	public BoxFolder.Info createFolder(BoxFolder parentFolder,
			String childFolderName) {
		BoxFolder.Info folderInfo = checkFolderExist(parentFolder,
				childFolderName);
		if (folderInfo == null) {
			BoxFolder.Info childFolderInfo = parentFolder
					.createFolder(childFolderName);
			return childFolderInfo;
		} else {
			return folderInfo;
		}
	}

	public void deleteFolder(String folderId) {
		BoxFolder folder = new BoxFolder(api, folderId);
		folder.delete(true);
	}

	public BoxFolder.Info checkFolderExist(BoxFolder parentFolder,
			String childFolderName) {
		for (BoxItem.Info itemInfo : parentFolder) {
			if (itemInfo instanceof BoxFolder.Info) {
				if (itemInfo.getName().equals(childFolderName))
					return (BoxFolder.Info) itemInfo;
			}
		}
		return null;
	}

}
