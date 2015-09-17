<%@ page import="manna.it.bean.EquipmentBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ page import = "java.awt.Color" %>
<%@ page import = "java.awt.Graphics2D" %>
<%@ page import = "java.awt.image.BufferedImage" %>
<%@ page import = "com.swetake.util.Qrcode, com.sun.image.codec.jpeg.*" %>
<%@ page import = "javax.servlet.http.HttpServletResponse"%>

<!doctype html>
<html>
<head>
<script type="text/javascript" src="../jquery/jquery-2.1.3.js"></script>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<!-- // jQuery 기본 js파일  -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<!-- //jQuery UI 라이브러리 js파일 -->
 <script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<%!

public void qrcodeMake(String arg, HttpServletResponse res) throws Exception {

 Qrcode qrcode=new Qrcode();
 qrcode.setQrcodeErrorCorrect('M');
 qrcode.setQrcodeEncodeMode('B');
 qrcode.setQrcodeVersion(7);

 BufferedImage bi = new BufferedImage(139, 139, BufferedImage.TYPE_INT_RGB);

 Graphics2D g = bi.createGraphics();
 g.setBackground(Color.WHITE);
 g.clearRect(0, 0, 139, 139);

 g.setColor(Color.BLACK);
 byte[] d = null;

  d =arg.getBytes("8859_1");
  if (d.length>0 && d.length <123){
   boolean[][] b = qrcode.calQrcode(d);
   for (int i=0;i<b.length;i++){
    for (int j=0;j<b.length;j++){
     if (b[j][i]) {
      g.fillRect(j*3+2,i*3+2,3,3);
     }
    }
   }
  }
 g.dispose();
 bi.flush();

 res.setContentType("image/jpeg");
 ServletOutputStream os = res.getOutputStream();
 JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
 encoder.encode(bi);
 os.flush();
 os.close();
}

%>


<script type="text/javascript">
	 $(document).ready(function(){
		
	    $("#back").click(function(){
	     	history.go(-1);	
	    });
	    $(".modify").click(function(){
	    	 var num = $(this).parent().parent().parent().children().children('.no').html();
	    	 alert(num);
				var loc ="/equipmentModifyView.eq?num="+num;
				location.href(loc); 
	    });
	 });
	 
	 
</script>
<style type="text/css">
		table {
			border-collapse: collapse;
			border : 1px;
			text-align: center;
			margin:0px;
			padding :0px;
			cellpadding:5px;
			cellspacing:0px;
		}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>장비수정</title>

</head>
<body>
	<div class="equipment">


		<div id="title"	style="float: center; margin-top: 15px; font-family: 굴림; font-size: 20pt;">
			<h2>장비view</h2>
		</div>
		<div id="title" style="float: center; margin-top: 15px; font-family: 굴림; font-size: 10pt;">
			
	<%
 		String qrString ="덕그니 \nhttp://comjakma.blog.me/";
 		qrcodeMake(qrString, response);
%>

			

				<table border=1 class="add">
					<tr>
						<th>장비코드</th>
						<td class="no">${ev.num}</td>
					</tr>
					<tr>
						<th>장비명</th>
						<td>${ev.eq_name}</td>
					</tr>
					<tr>
						<th>제조사</th>
						<td>${ev.manufacturer}</td>
					</tr>
					<tr>
						<th>장비분류</th>
						<td>
							${ev.eq_category}
						</td>
					</tr>
					<tr>
						<th>구입날짜</th>
						<td>
							${ev.date}
						</td>
					</tr>
					<tr>
						<td colspan=11 style="text-align: center">
							<input type="button" class= "modify" value="수정">
							<input type="button" id = "back" value="뒤로">
						</td>
					</tr>
				</table>
			
		</div>
	</div>
</body>
</html>