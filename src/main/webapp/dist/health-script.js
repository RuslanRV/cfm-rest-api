var requestCounts = 0;

$(function($) {
	
	setTimeout(function() {
		$("#health-of-environments .container").slideDown(1000);
	}, 1000);
	
	
	buildAccordeon();
	reloadData();
	init();
	
// Clicking on "Refresh" button refreshs environments content
	$(document).on("click", "#refresh", function () {
		reloadData();
	});
	
// Start of Loading Data 	
	function init(){
		setInterval(function(){
			reloadData();
		}, 300000);
	}
	
// Reloading Data 	
	function reloadData(){
		if(requestCounts > 0){
			return;
		}
		
		// Show loader
		$("#environments-loader").show();
		requestCounts = 6; 
		
		$(".hiding-script").empty();
		refresher();
	}

// Refreshing Data 
	function refresher(){
		urlBuilder(window.location.protocol + "//"+window.location.host+"/cfm-rest-api-1.0/rest-api/services/swaggerRestService/fatchDevHealthCheck", "DevHealthCheck");		
		urlBuilder(window.location.protocol + "//"+window.location.host+"/cfm-rest-api-1.0/rest-api/services/swaggerRestService/fatchDevMetricRegistry", "DevMetricRegistry");		
		urlBuilder(window.location.protocol + "//"+window.location.host+"/cfm-rest-api-1.0/rest-api/services/swaggerRestService/fatchProdHealthCheck", "ProdHealthCheck");		
		urlBuilder(window.location.protocol + "//"+window.location.host+"/cfm-rest-api-1.0/rest-api/services/swaggerRestService/fatchProdMetricRegistry", "ProdMetricRegistry");
	}
	
	window.reloadData = reloadData;
	
// Calling data to Build In Tables 
	function urlBuilder(url, environment){
		
		$.ajax({
			dataType: "json",
			url: url,
			success: function(data){
				requestCounts--;
				data = data || {};
				data.status = data.status || "GREEN";
				build(data, environment);
			},
			async: true,
			error: function() {
				$("#environments-loader").hide();
				data = {};
				data.status = "RED";
				build(data, environment);
				requestCounts--;
			}
		});
		
	}
	function buildHtml(object){
	var html = "";
		
		$.each(object, function(key, value){
			if(key == "errorMessage" || key == "success" || key == "status"){
				return true;
			}
			
			if($.type(value) == "object"){
				// Accordion
				html += "<div class='accordion'> " + "<h5> <span> <span> <a href='javascript:;' class='toggleOperation'>&#149;&nbsp;" +key+ "</a> </span> </span> </h5> <div class='hide-environments-content position'> <table>";
				html += buildHtml(value);
				html += "</table> </div> </div>";
			} else if($.type(value) == "array"){
				// Build array value with table element
				html += "<div class='accordion'> " + "<h5> <span> <span> <a href='javascript:;' class='toggleOperation'>&#149;&nbsp;" +key+ "</a> </span> </span> </h5> <div class='hide-environments-content position'>";
				html += buildTable(value);
				html += "</div> </div>";
			} else{
				// Build simple table
				html += buildDiv(key, value);
			}
			
		});
		return html;
	}
	// Building In Tables 
	function build(object, environments){
	
		var html = buildHtml(object);
		// Building Content Style depending on Object status color
		var $link = undefined;
		switch(environments){
			case "DevHealthCheck":
				$link = $(".dev_health_check");
				break;
			case "DevMetricRegistry":
				$link = $(".dev_metric_registry");
				break;
			case "ProdHealthCheck":
				$link = $(".prod_health_check");
				break;
			case "ProdMetricRegistry":
				$link = $(".prod_metric_registry");
				break;
		}		
		
		buildEnvironmentsStyle(object, $link, html);
		
		if(requestCounts <= 0){			
			$("#environments-loader").hide();
			
		}
		
	}

	// Building in Tables
	function buildTable(array){
		var table = '<table>';
		$.each(array, function(key, object){
				
			$.each(object, function(objectKey, objectValue){
				table += "<tr>";
				table += "<td style='width: 30%;'><b>" + objectKey + "</b></td><td>" + objectValue + "</td>";
				table += "</tr>";
			});
				
		});
		
		table+= "</table>";
		return table;
	}
	
// Building Divs
	function buildDiv(key, value){
		return "<tr><td style='width: 30%;'><b>" + key + "</b></td><td>" + value + "</td></tr>";
	}

// Building Content Style depending on Object status color
	function buildEnvironmentsStyle(object, link, html){
		link.find(".content").html(html);
		var env = link.parent().parent();
		var envstatus = env.data("envstatus") || "GREEN";
		if(object.status == "RED"){
			env.data("envstatus", "RED");
			link.find("a").addClass("envir-header-red");
		}else if(object.status == "GREEN"){
			link.find("a").addClass("envir-header-green");
		}else if(object.status == "YELLOW"){
			if(envstatus == "GREEN"){
				env.data("envstatus", "YELLOW");
			}
			link.find("a").addClass("envir-header-yellow");
		}
		
		envstatus = env.data("envstatus") || "GREEN";
		if(envstatus == "GREEN"){
			makeGreen(env);
		}else if(envstatus == "YELLOW"){
			makeYellow(env);
		}else if(envstatus == "RED"){
			makeRed(env);
		}
	}
	
	// Status colors
	function makeRed(env){
		env.addClass("background_red");
		env.find(".envire_name").removeClass("heading_green").removeClass("heading_yellow").addClass("heading_red");
		env.find(".envire_content").removeClass("content_green").removeClass("content_yellow").addClass("content_red");
		env.find(".status_color").removeClass("green").removeClass("yellow").addClass("red");
		env.find(".toggle_headers").removeClass("divider-green").removeClass("divider-yellow").addClass("divider-red");
		env.find(".status_color").text("Red");
	}
	
	function makeGreen(env){
		env.addClass("background_green");
		env.find(".envire_name").removeClass("heading_red").removeClass("heading_yellow").addClass("heading_green");
		env.find(".envire_content").removeClass("content_red").removeClass("content_yellow").addClass("content_green");
		env.find(".status_color").removeClass("red").removeClass("yellow").addClass("green");
		env.find(".toggle_headers").removeClass("divider-red").removeClass("divider-yellow").addClass("divider-green");
		env.find(".status_color").text("Green");
	}
	
	function makeYellow(env){
		env.addClass("background_yellow");
		env.find(".envire_name").removeClass("heading_green").removeClass("heading_red").addClass("heading_yellow");
		env.find(".envire_content").removeClass("content_green").removeClass("content_red").addClass("content_yellow");
		env.find(".status_color").removeClass("green").removeClass("red").addClass("yellow");
		env.find(".toggle_headers").removeClass("divider-green").removeClass("divider-red").addClass("divider-yellow");
		env.find(".status_color").text("Yellow");
	}
	
//Toggle Functionality
	function buildAccordeon(){
		$(document).off("click", "#health-of-environments .toggleOperation").on("click", "#health-of-environments .toggleOperation", function () {
		   $(this).parent().parent().parent().next().slideToggle();
		});
	}
	
});