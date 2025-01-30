var OmegaPezza;

function switchLogo() {
	var logo = document.getElementById('logo');
	var logoSrc = window.innerWidth <= 850 ? "img/logo.png" : "img/logoName.png";
	logo.src = logoSrc;
}



function Ricerca(size) {
	let filtro;
	if (size == "big") filtro = document.getElementById("input").value;
	else if (size == "small") filtro = document.getElementById("inputbar").value;
	if (filtro == null) filtro = "";
	var currentURL = window.location.href;
	window.location.href = currentURL.substring(0, currentURL.lastIndexOf('/') + 1) + 'Shop?filter=' + filtro.toLowerCase() + '&action=ricerca'

}

/*function RicercaCat(tipo){
	   //let filtro = tipo;
	   console.log(tipo)
	   var currentURL = window.location.href;
	   window.location.href =  currentURL.substring(0, currentURL.lastIndexOf('/') + 1) + 'Shop?filter=' + tipo + '&action=categoria' 

}
*/

function openSidebar(id) {
	const sidebar = document.getElementById("sideb");

	switch (id) {
		case "isOpenSidebar":
			console.log("Clicked sidebar toggle");
			if (sidebar.classList.contains("show")) {
				sidebar.classList.remove("show");
				sidebar.classList.add("hidden");
			}
			else {
				sidebar.classList.add("show");
				sidebar.classList.remove("hidden");
			}
			break;

		case "sideover":
			console.log("Clicked overlay toggle");
			sidebar.classList.remove("show");
			sidebar.classList.add("hidden");
			break;

		case "sidex":
			console.log("Clicked sidebar button toggle");
			sidebar.classList.remove("show");
			sidebar.classList.add("hidden");
			break;
	}
}

function openSearchbar(id) {
	const searchbar = document.getElementById("sorciobarra");

	switch (id) {
		case "searchIconBtn":
			console.log("Clicked searchbar toggle");
			if (searchbar.classList.contains("show")) {
				searchbar.classList.remove("show");
				searchbar.classList.add("hidden");
			}
			else {
				searchbar.classList.add("show");
				searchbar.classList.remove("hidden");
			}
			break;

		case "sorcioover":
			console.log("Clicked overlay toggle");
			searchbar.classList.remove("show");
			searchbar.classList.add("hidden");
			break;

		case "close":
			console.log("Clicked sidebar button toggle");
			searchbar.classList.remove("show");
			searchbar.classList.add("hidden");
			break;
	}

}

function closeSearchbar() {
	const searchbar = document.getElementById("sorciobarra");
	searchbar.classList.remove("show");
	searchbar.classList.add("hidden");
}

function checkWindowWidth() {
	const searchbar = document.querySelector(".searchbar");
	if (window.innerWidth > 850 && searchbar.classList.contains("show")) {
		closeSearchbar();
	}
}


function countElements(cls) {
	const parentElement = document.getElementById('header-block');
	const numberOfElements = parentElement.getElementsByClassName(cls).length;
	return numberOfElements;
}

function toggleResize() {
	const styleSheet = document.getElementById('light.css');
	if (countElements("cartContainer") == 4) document.getElementById("toggle").classList.add('banana');
}

function switchSuite() {
	switchLogo();
	checkWindowWidth();
	toggleResize();
}






function scomparisci() {
	let sidepanel = document.querySelector('#sidepanel');
	let toggle = document.querySelector('.extendBtn');
	sidepanel.classList.toggle('active');
}

function brutta() {
	let sidepanel = document.querySelector('#sidepanel');
	if (sidepanel.classList.contains('active') && window.innerWidth <= 650) sidepanel.classList.toggle('active');
}

function bailandoo() {
	let sidepanel = document.querySelector('#sidepanel');
	let sideBtn = document.querySelector('.sidepanelBtn');
	let btnIcon = document.querySelector('#btnIcon');
	sidepanel.classList.toggle('open');
	btnIcon.src = sidepanel.classList.contains('open') ? '../img/icon/iconX.png' : '../img/icon/iconMenu.png';
}



function consoleText(words, id, colors) {
	if (colors === undefined) colors = ['#fff'];
	var visible = true;
	var con = document.getElementById('console');
	var letterCount = 1;
	var x = 1;
	var waiting = false;
	var target = document.getElementById(id)
	target.setAttribute('style', 'color:' + colors[0])

	window.setInterval(function() {
		if (letterCount === 0 && waiting === false) {
			waiting = true;
			target.innerHTML = words[0].substring(0, letterCount)

			window.setTimeout(function() {
				var usedColor = colors.shift();
				colors.push(usedColor);
				var usedWord = words.shift();
				words.push(usedWord);
				x = 1;
				target.setAttribute('style', 'color:' + colors[0])
				letterCount += x;
				waiting = false;
			}, 1000)

		} else if (letterCount === words[0].length + 1 && waiting === false) {
			waiting = true;
			window.setTimeout(function() {
				x = -1;
				letterCount += x;
				waiting = false;
			}, 1000)

		} else if (waiting === false) {
			target.innerHTML = words[0].substring(0, letterCount)
			letterCount += x;
		}
	}, 120)

	window.setInterval(function() {
		if (visible === true) {
			con.className = 'console-underscore hidden'
			visible = false;

		} else {
			con.className = 'console-underscore'
			visible = true;
		}
	}, 400)
}


function modificaCarrello(azione) {
	const qty = document.getElementById('inputQty').value;
	var c = OmegaPezza;
	
	var xhr = new XMLHttpRequest();


	var finalString = "CartServlet?id=" + c + "&action=" + azione + "&quantity=" + qty;
	console.log(finalString);

	xhr.open("GET", finalString, true);



	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			console.log("Oggetto aggiunto con successo al carrello!");
			var numeroElementi = xhr.responseText;
			console.log("elementi =" + numeroElementi);
			document.getElementById("contatoreCarrello").innerHTML = numeroElementi;
		}
	};

	xhr.send();

	closePopupFunc();
}

// Function to open the popup

function openPopup(id) {
	OmegaPezza = id;
	console.log(OmegaPezza);
	const popupOverlay = document.getElementById('popupOverlay');
	popupOverlay.style.display = 'block';
}

// Function to close the popup

function closePopupFunc() {
	const popupOverlay = document.getElementById('popupOverlay');
	popupOverlay.style.display = 'none';
}

// Function to submit the signup form

function submitForm() {
	const popup = document.getElementById('popup');
	const closePopup = document.getElementById('closePopup');
	const emailInput = document.getElementById('emailInput');
	const email = emailInput.value;
	// Add your form submission logic here
	console.log(`Email submitted: ${email}`);
	 // Close the popup after form submission

}
