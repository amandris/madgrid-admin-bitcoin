fixMozillaZIndex=true; //Fixes Z-Index problem  with Mozilla browsers but causes odd scrolling problem, toggle to see if it helps
_menuCloseDelay=500;
_menuOpenDelay=1500;
_subOffsetTop=2;
_subOffsetLeft=-2;


with(menuStyle=new mm_style()){
bordercolor="#000000";
borderstyle="solid";
borderwidth=0;
fontfamily="Verdana, Tahoma, Arial";
fontsize="12";
fontstyle="normal";
headerbgcolor="#FFFFFF";
headercolor="#000000";
offbgcolor="#AAAAAA";
offcolor="#FFFFFF";
onbgcolor="#FFFFFF";
oncolor="#AAAAAA";
padding=5;
pagebgcolor="#AAAAAA";
pagecolor="#FFFFFF";
separatorcolor="#AAAAAA";
separatorsize=10;
}

with(subMenuStyle=new mm_style()){
bordercolor="#000000";
borderstyle="solid";
borderwidth=0;
fontfamily="Verdana, Tahoma, Arial";
fontsize="12";
fontstyle="normal";
headerbgcolor="#ffffff";
headercolor="#000000";
offbgcolor="#AAAAAA";
offcolor="#FFFFFF";
onbgcolor="#FFFFFF";
oncolor="#AAAAAA";
outfilter="Fade(duration=0.3)";
overfilter="Fade(duration=0.3);Alpha(opacity=85);Shadow(color=#777777', Direction=135, Strength=3)";
padding=3;
pagebgcolor="#AAAAAA";
pagecolor="#FFFFFF";
separatorcolor="#EEEEEE";
separatorsize=1;
}
