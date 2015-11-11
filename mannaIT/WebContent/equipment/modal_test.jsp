<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
 <HEAD>
  <TITLE>Modal Window Example</TITLE>
  <script type="text/javascript" src="/common/jquery/jquery-2.1.3.js"></script>
  <SCRIPT TYPE="text/javascript">

  function openModal(width, height) {
	    var top = $("#modal_back").height() / 2 - height / 2;
	    var left = $("#modal_back").width() / 2 - width / 2;
	    
	    $('#modal').css("top", top);
	    $('#modal').css("left", left);
	    $('#modal').css("width", width);
	    $('#modal').css("height", height);
	    $('#modal_back').css("display", "block");
	   }

	   function closeModal() {
	    $('#modal_back').css("display", "none");
	   }
  </SCRIPT>
  <STYLE>
   #modal_back { position:absolute; top:0; left:0; width:100%; height:100%; display:none; background:rgba(0,0,0,0.5); }
   #modal { position:absolute; background:#FFF; color:#000; }
   #modal a { color:#000; }
  </STYLE>
 </HEAD>

 <BODY>
  <a href="javascript:openModal(300, 200)">Click</a>

  <div id="modal_back">
   <div id="modal">
    <a href="javascript:closeModal()">Close</a>
   </div>
  </div>
 </BODY>
</HTML> 


