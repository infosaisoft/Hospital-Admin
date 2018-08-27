

$(document).ready(function(){	
	
		// menu left side
		$("#menu_tgl_btn").click(function(){
			$("#id_body").toggleClass('hide_menu');
			$(this).find("i").toggleClass("fa-outdent fa-indent");
		});
		// Menu
		$(".left_sub_menu").hide(); 
		$(".left_main_menu").click(function(e){
			e.preventDefault();
			$(this).parent().find("ul").slideToggle('fast'); 
			$(this).parent().find(".fa-angle-right").toggleClass('rotate90'); 
			$(this).parent().siblings().find("ul").slideUp('fast'); 
			$(this).parent().siblings().find(".fa-angle-right").removeClass('rotate90');
		});
	
});