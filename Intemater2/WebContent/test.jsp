<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
<title>Untitled Document</title>
<style>
html, body { height: 100%; width: 80%; padding: 0; margin: 0; }
div { width: 50%; height: 35%; float: left; }
#div1 {  }
#div2 {  }
#div3 { }
#div4 { }
.style1 {
	color: #006699;
	font-weight: bold;
}
</style>
</head>

<body>
			
<div id="div1" align="center">
    <br /><br /><br /><br />
	<select size="1">
	 <option>Select Option</option>
	 <option>Option1</option>
	 <option>Option2</option>
	 <option>Option3</option>
	 <option>Option4</option>
      </select>
</div>
<div id="div2">
      <table width="360" border="1">
  <caption>
    Email
  </caption>
  <tr>
    <td width="66" height="22">Subject</td>
    <td width="10">:</td>
    <td width="390"><input type="text" name="subject" id="idSubject" size="50"/></td>
  </tr>
  <tr>
    <td>Message</td>
    <td>:</td>
    <td>  <textarea name="message"  id="idMessage" cols="49" rows="8"></textarea> </td>
  </tr>
</table>

</div>
<br /><br />
<div id="div3">
  <table width="100%" border="1" align="left">
  <caption>
    Customer List
  </caption>
  <tr>
    <td width="45" height="47"><span class="style1">Sr. No. </span></td>
    <td width="65"><span class="style1">Cust. ID </span></td>
    <td width="296" align="center"><span class="style1"> Cust. Names </span></td>
    <td width="86"><span class="style1">Email Status </span></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>

</div>
<div id="div4"  align="center">
    <br /><br /><br />
				<input type="submit" name="submit" value="Send Emails" id="idSubmit"/>
</div>
	
</body>
</html>
