package com.wisewater.util.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.wisewater.util.tools.FileCopyUtil;
import com.wisewater.util.tools.LoadConstant;

import net.sf.json.JSONObject;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	LoadConstant lc;

	/**
	 * 上传图文图片
	 * 
	 * @param request
	 * @param file
	 * @param token
	 * @return jsonString AlexFung 2015年4月8日 下午4:07:09
	 */
	public String uploadArticleImg(HttpServletRequest request,
			MultipartFile file, String token) {
		String savePath = request.getSession().getServletContext()
				.getRealPath("\\")
				+ "resources\\attached\\";
		String saveUrl =  "/resources/attached/";
		if (file == null) {
			return getError("请选择文件。");
		}
		if (file.getSize() > 2000000) {
			String str = "上传失败文件大小不能超过2M.";
			String newStr = "";
			byte[] tbyte;
			try {
				tbyte = str.getBytes("UTF-8");
				newStr = new String(tbyte,"ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return getError(newStr);
		}
		// 得到文件名
		String realname = file.getOriginalFilename();
		String imgType = realname.substring(realname.lastIndexOf(".") + 1)
				.toLowerCase();
		String filename = "";
		// 创建文件夹
		savePath += token + "/";
		saveUrl += token + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsss");
		String ymd = sdf.format(new Date());
		filename = ymd;
		if (file.getSize() > 0) {
			try {
				SaveFileFromInputStream(file.getInputStream(), savePath,
						filename + "." + imgType);
				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", lc.getWebSitePath() + saveUrl + filename + "."
						+ imgType);
				System.out.println(obj.toString());
				return obj.toString();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				return null;
			}
		} else {
			return getError("上传失败：上传文件不能为空!");
		}
	}

	/**
	 * 上传图片
	 * 
	 * @param request
	 * @param file
	 * @param token
	 * @return picName AlexFung 2015年4月8日 下午4:07:09
	 */
	public String uploadImg(HttpServletRequest request, MultipartFile file,
			String token) {
		String savePath = request.getSession().getServletContext()
				.getRealPath("\\")
				+ "resources\\attached\\";
		String saveUrl = request.getContextPath() + "/resources/attached/";
		if (file == null) {
			return getError("请选择文件。");
		}
		if (file.getSize() > 2000000) {
			String str = "上传失败文件大小不能超过2M.";
			String newStr = "";
			byte[] tbyte;
			try {
				tbyte = str.getBytes("UTF-8");
				newStr = new String(tbyte,"ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return getError(newStr);
		}
		// 得到文件名
		String realname = file.getOriginalFilename();
		String imgType = realname.substring(realname.lastIndexOf(".") + 1)
				.toLowerCase();
		String filename = "";
		// 创建文件夹
		savePath += token + "/";
		saveUrl += token + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsss");
		String ymd = sdf.format(new Date());
		filename = ymd;
		if (file.getSize() > 0) {
			try {
				SaveFileFromInputStream(file.getInputStream(), savePath,
						filename + "." + imgType);
				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", saveUrl + filename + "." + imgType);
				System.out.println(obj.toString());
				return filename + "." + imgType;
			} catch (IOException e) {
				System.out.println(e.getMessage());
				return null;
			}
		} else {
			return getError("上传失败：上传文件不能为空!");
		}
	}

	/**
	 * 处理文本编辑器里图片复制
	 * 
	 * @param content
	 * @param token
	 *            XingXingLvCha 2015年4月20日 下午2:09:41
	 */
	public String handleContentImg(String content, String token) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String savePath = request.getSession().getServletContext()
				.getRealPath("\\")
				+ "resources\\attached\\" + token + "/";
		String saveUrl = request.getContextPath() + "/resources/attached/"
				+ token + "/";
		String[] imgList = FileCopyUtil.getImgs(content);
		if (imgList != null && imgList.length > 0) {
			for (int num = 0; num < imgList.length; num++) {
				if (imgList[num].contains("http://")) // 外链图片
				{
					break;
					/*
					 * String oldUrl = imgList[num]; String imgType =
					 * oldUrl.substring
					 * (oldUrl.lastIndexOf(".")+1).toLowerCase();
					 * if(!"bmp".equals(imgType) && !"jpg".equals(imgType) &&
					 * !"png".equals(imgType) && !"tiff".equals(imgType) &&
					 * !"gif".equals(imgType)) { break; }
					 * 
					 * String filename = new
					 * SimpleDateFormat("yyyyMMddHHmmsss").format(new Date());
					 * String newUrl = savePath + filename + "." + imgType;
					 * String newSaveUrl = saveUrl + filename + "." + imgType;
					 * boolean result = FileCopyUtil.copyFile(oldUrl, newUrl);
					 * if(result) { content = content.replaceAll(oldUrl,
					 * newSaveUrl); }
					 */
				} else if (!imgList[num].contains(token)) // 内部图片，路径不一样
				{
					String oldUrl = imgList[num];
					String oldFileName = oldUrl.substring(oldUrl
							.lastIndexOf("/") + 1);

					String toOldUrl = request.getSession().getServletContext()
							.getRealPath("\\")
							+ "resources\\attached\\" + getSubStr(oldUrl, 2);
					String newUrl = savePath + oldFileName;
					String newSaveUrl = saveUrl + oldFileName;
					boolean result = FileCopyUtil.copyFile(toOldUrl, newUrl);
					if (result) {
						content = content.replaceAll(oldUrl, newSaveUrl);
					}

				}

			}
		}
		return content;
	}

	/**
	 * 上传图文图片
	 * 
	 * @param request
	 * @param file
	 * @return XingXingLvCha 2015年4月2日 下午6:54:30
	 */
	public String uploadIT(HttpServletRequest request, MultipartFile file,
			String token) {
		String savePath = request.getSession().getServletContext()
				.getRealPath("\\")
				+ "resources\\attached\\";
		String saveUrl = request.getContextPath() + "/resources/attached/";
		if (file == null) {
			return getError("请选择文件。");
		}
		if (file.getSize() > 2000000) {
			String str = "上传失败文件大小不能超过2M.";
			String newStr = "";
			byte[] tbyte;
			try {
				tbyte = str.getBytes("UTF-8");
				newStr = new String(tbyte,"ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return getError(newStr);
		}
		// 得到文件名
		String realname = file.getOriginalFilename();
		String imgType = realname.substring(realname.lastIndexOf(".") + 1)
				.toLowerCase();
		String filename = "";
		// 创建文件夹
		savePath += token + "/";
		saveUrl += token + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsss");
		String ymd = sdf.format(new Date());
		filename = ymd;
		if (file.getSize() > 0) {
			try {
				SaveFileFromInputStream(file.getInputStream(), savePath,
						filename + "." + imgType);
				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", saveUrl + filename + "." + imgType);
				System.out.println(obj.toString());
				return filename + "." + imgType;
			} catch (IOException e) {
				System.out.println(e.getMessage());
				return null;
			}
		} else {
			return getError("上传失败：上传文件不能为空!");
		}
	}

	/**
	 * 上传图文图片
	 * 
	 * @param request
	 * @param file
	 * @return
	 */
	public String uploadImgIT(HttpServletRequest request, MultipartFile file,
			String token) {
		String savePath = request.getSession().getServletContext()
				.getRealPath("\\")
				+ "resources\\attached\\";
		String saveUrl = request.getContextPath() + "/resources/attached/";
		if (file == null) {
			return getError("请选择文件。");
		}
		if (file.getSize() > 2000000) {
			String str = "上传失败文件大小不能超过2M.";
			String newStr = "";
			byte[] tbyte;
			try {
				tbyte = str.getBytes("UTF-8");
				newStr = new String(tbyte,"ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return getError(newStr);
		}
		// 得到文件名
		String realname = file.getOriginalFilename();
		String imgType = realname.substring(realname.lastIndexOf(".") + 1)
				.toLowerCase();
		String filename = "";
		// 创建文件夹
		savePath += token + "/";
		saveUrl += token + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsss");
		String ymd = sdf.format(new Date());
		filename = ymd;
		if (file.getSize() > 0) {
			try {
				SaveFileFromInputStream(file.getInputStream(), savePath,
						filename + "." + imgType);
				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", saveUrl + filename + "." + imgType);
				System.out.println(obj.toString());
				return obj.toString();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				return null;
			}
		} else {
			return getError("上传失败：上传文件不能为空!");
		}
	}

	/**
	 * 保存文件
	 * 
	 * @param stream
	 * @param path
	 * @param filename
	 * @throws IOException
	 */
	public void SaveFileFromInputStream(InputStream stream, String path,
			String filename) throws IOException {
		FileOutputStream fs = new FileOutputStream(path + "/" + filename);
		byte[] buffer = new byte[1024 * 1024];
		int bytesum = 0;
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
	}

	/**
	 * 获取错误方法
	 * 
	 * @param message
	 * @return
	 */
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toString();
	}

	private String getSubStr(String str, int num) {
		String result = "";
		int i = 0;
		while (i < num) {
			int lastFirst = str.lastIndexOf('/');
			result = str.substring(lastFirst) + result;
			str = str.substring(0, lastFirst);
			i++;
		}
		return result.substring(1);
	}
}
