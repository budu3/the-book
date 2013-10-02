#Chapter 1
##The Setup

To begin making web apps you need the following;

1. Chrome browser
2. An editor (Sublime Text)
3. Android SDK (You can download this from http://developer.android.com/sdk/index.html)

_Step 1_

Copy and paste the code listing in Exercise 1 into your editor. Save the file as “index.html”.

_Step 2_

Open “index.html” in your Chrome browser. You should see your simple TODO list app.

_Step 3_

Copy “index.html” into the asset folder in Eclipse workspace.

_Step 4_

Copy and paste the following code listing into you Eclipse workspace

	package com.example.mytodolist;

	import android.os.Bundle;
	import android.app.Activity;
	import android.view.Menu;
	import android.webkit.WebView;

	public class MainActivity extends Activity {

		private WebView webView;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        
	        webView = (WebView) findViewById(R.id.webView1);
			webView.getSettings().setJavaScriptEnabled(true);
			//webView.loadUrl("http://www.google.com");
			webView.loadUrl("file:///android_asset/index.html");
	        //webView.loadUrl("file:///android_asset/index.html");
	    }


	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
	    
	}

_Step 6_

Click the run button in Eclipse and your app should show up in the emulator.

A Simple Web App

In this section we are going to create a simple to-do list app. This app will run from our browser. Later on we will learn how to run this app on a mobile phone.

This simple app consists of a list of “todo” items, a text input box and a submit button. You include items into you list by entering them into the text input box and then pressing the submit button. You input will them appear on the list.

We achieve these app functionalities by embedding javascript code into the HTML page. The javascript code manipulates the HTML page's DOM. In the javascript code we attach a click event listener to the button that listens for when the submit button is pressed. When it is pressed, whatever is entered into the textbox is appended to the todo list.

	<script>
		$("#submit-btn").click(transfer);
		function transfer(){
			var item = $("#add-item").val();
			$("ul").append("<li>" + item + "</li>")
		}
	</script>

	Type and save the code list below as “todo.html” then open it in your browser.

	Exercise 1

	<!DOCTYPE html>
	<html>
	<head>
	<title>todo list</title>
	<link href="css/main.css" rel="stylesheet">
	</head>
	<body>
	<div class="container">
		<ul class="items">
			<li class="item">Don't forget the milk</li>
		</ul>
		<input type="text" id="add-item"></input><input type="submit" id="submit-btn"></input>
	</div>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.1/jquery-ui.min.js"></script>
	<script>
		$("#submit-btn").click(transfer);
		function transfer(){
			var item = $("#add-item").val();
			$("ul").append("<li>" + item + "</li>")
		}
		$("ul").sortable({appendTo:"body"});
	</script>
	</body>
	</html>























Exercise 3

In this section we will simply add the ability to mark a task as done by striking through it.







































Strike through

	<!DOCTYPE html>
	<html>
	<head>
	<title>todo list</title>
	</head>
	<body>

	<div class="container">
		<ul>
			<li class="item" >Don't forget the milk</li>
		</ul>
			<input type="text" id="add-item"></input><input type="submit" id="submit-btn"></input>
	</div>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.1/jquery-ui.min.js"></script>
	<script>

		$('#submit-btn').click(transfer);

		function transfer(){
			var item = $('#add-item').val();
			$('ul').append('<li>' + item + '</li>');
			striketru();
		}

		$('ul').sortable({appendTo:'body'});


		function striketru(){
			$('li').click(
				function(){
					var curr = $(this);
					if (curr.css('text-decoration') === 'none'){
						console.log(curr.css('text-decoration'));
						curr.css('text-decoration', 'line-through');
					}
				}
			);
		}

		striketru();
		
	</script>
	</body>
	</html>

Exercise 4
Delete

!DOCTYPE html>
	<html>
	<head>
	<title>todo list</title>
	</head>
	<body>

	<div class="container">
		<ul>
			<li class="item" >Don't forget the milk <img src="images/delete.png" class="del"></img></li>
		</ul>
			<input type="text" id="add-item"></input><input type="submit" id="submit-btn"></input>
	</div>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.1/jquery-ui.min.js"></script>
	<script>

		$('#submit-btn').click(transfer);

		function transfer(){
			var item = $('#add-item').val();
			$('ul').append('<li>' + item + '<img src="images/delete.png" class="del"></img></li>');
			striketru();
			remove();
		}

		$('ul').sortable({appendTo:'body'});

		function striketru(){
			$('li').click(
				function(){
					var curr = $(this);
					if (curr.css('text-decoration') === 'none'){
						console.log(curr.css('text-decoration'));
						curr.css('text-decoration', 'line-through');
					}
				}
			);
		}

		
		function remove(){
			$('.del').click(
				function(){
					var parent = $(this).parent();
					$(parent).css('display', 'none');
					console.log(parent);
				}
			);
		}
		

		striketru();
		remove();
		
	</script>
	</body>
	</html>
