function start(){

	var title = document.title;
	title	= "Billy's Portfolio";
	var logo = '<div id="logo">' + '<a href="index.html">'
		+ '</a>' + '</div>';
	
	// Your navigation information
	var navigation = 	'<div>'+
							'<div id="nav">'+
							'<div> <a href="index.html">Home</a></div><div><a  href="about.html">About</a></div><div> <a  href="classes.html">Classes</a></div> <div> <a  href="projects.html">Projects</a></div><div><a  href="contact.html">Contact</a></div>  <div> <a  href="sitemap.html">Sitemap</a></div>'+
							'<p style="clear: both;"></p>' +
						'</div></div>';

	// Your footer information
	var footer = '<div id="footer"><a href="index.html">Home</a> | <a  href="classes.html">Classes</a> | <a  href="projects.html">Projects</a> | <a  href="contact.html">Contact</a> | <a  href="sitemap.html">Sitemap</a> | <a id="change_style" title="Click here to change style" class="tooltip" onCLick="javscript:changeit();"><b>Change Style</b></a> <div id="clock"></div> <div id="copyright"> &copy;Billy Lee 2015</div></div>';
	// Keep track of what is in the current body.
	var temp_body = document.body.innerHTML;
	// Modify the header and footer to the body.
	// Modify the header and footer to the body.
	document.body.innerHTML = 	'<link id="cssstylechange" rel="stylesheet" type="text/css" href="style.css"></link><div id="content"><div id="headercolor">' + navigation + '</div>' + 
								'<div id="bod" style="padding: 10px;">' + 
								temp_body + 
								'</div>' + 
								footer + '</div>';
startclock();
}

function changeit(){
 var s = document.getElementById('cssstylechange');
 s.href="style2.css";
}

var index = 0;

setInterval(function() {
	var imagearray = ["url(me.jpg)", "url(me2.jpg)", "url(me3.jpg)"];
	console.log(index);
	document.getElementById('mainpicture').style.backgroundImage = imagearray[index];
	console.log( document.getElementById('mainpicture').style.backgroundImage );
	++index;
	if (index >= imagearray.length)
		index = 0;
}, 2000);

function startclock()
{
	var clock = document.getElementById('clock');
	var _date = new Date();
	clock.innerHTML = Date();
}

setInterval(function(){
		startclock();
			}, 1000);