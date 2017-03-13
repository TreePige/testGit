/**
 * @title FontProvider.java
 * @package com.rongdu.eloan.modules.common.action
 * @author 吴国成
 * @version V1.0
 * created 2014年10月30日
 */
package com.rongdu.eloan.common.utils;

import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;

/**
 * itext生成pdf中文不显示问题，增加字体。
 * 
 * @version 1.0
 * @author 吴国成
 * @created 2014年10月30日 下午5:22:18
 */

public class PdfFontProvider extends XMLWorkerFontProvider {

	@Override
	public Font getFont(final String fontname, final String encoding,
			final boolean embedded, final float size, final int style,
			final BaseColor color) {
		BaseFont bf = null;
		try {
			bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Font font = new Font(bf, size, style, color);
		
		font.setColor(color);
		return font;
	}

}
