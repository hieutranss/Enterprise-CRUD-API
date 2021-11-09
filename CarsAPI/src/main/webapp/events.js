document.addEventListener("DOMContentLoaded", function() {
	// create a GET request to retrieve ALL movies, and add them to the table
	// 1. make an xhr object (ready state is 0)
	let xhr = new XMLHttpRequest();		// make HTTP requests
	// 2. define what happens during the AJAX call
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			console.log(JSON.parse(xhr.responseText));		 

			var carArray = JSON.parse(xhr.responseText);	

			carArray.forEach(carElement => {
				addCarToTable(carElement);
			});
		}
	}

	// 3. open the xhr call with the http request verb and the url
	xhr.open('GET', '/car/api/cars');

	// 4. send the ajax call
	xhr.send();
});

// low-level DOM Manipulation
function addCarToTable(car) {
	// creating all of our needed DOM elements
	var tr = document.createElement('tr');
	var name = document.createElement('td');
	var make = document.createElement('td');
	var model = document.createElement('td');
	var year = document.createElement('td');
	var engine_type = document.createElement('td');
	var transmission = document.createElement('td');
	var engine_transmission = document.createElement('td');
	var driveline = document.createElement('td');
	var fuel_type = document.createElement('td');
	var del = document.createElement('button');
	del.type = "button";
	del.id = "del-id";
	del.addEventListener('click', function(event) {
		deleteCar(car.id)
		var td = event.target.parentNode;
		var tr = td.parentNode;
		tr.parentNode.removeChild(tr);
	}, false);
	del.style = "border:white;text-align:center";
	del.innerHTML = "<i class='fas fa-minus-circle'></i>"

	var edit = document.createElement('button');
	edit.type = "button";
	edit.id = "edit-id";
	edit.setAttribute('data-bs-toggle', "modal");
	edit.setAttribute('data-bs-target', "#editModal");
	edit.addEventListener('click', function() {
		edit.addEventListener('show.bs.modal', function(event) {
			// Button that triggered the modal
			var button = event.relatedTarget
		})
		var oldCar = {
			id: car.id,
			name: car.name,
			model: car.model,
			make: car.make,
			year: car.year,
			engine_type: car.engine_type,
			transmission: car.transmission,
			engine_transmission: car.engine_transmission,
			driveLine: car.driveLine,
			fuel_type: car.fuel_type
		};
		editCar(oldCar)
	}, false);
	edit.style = "border:white;text-align:center";
	edit.innerHTML = "<i class='fas fa-user-edit'></i>"

	// adding data to the elements
	name.innerText = car.name;
	make.innerText = car.make;
	model.innerText = car.model;
	year.innerText = car.year;
	engine_type.innerText = car.engine_type;
	transmission.innerText = car.transmission;
	engine_transmission.innerText = car.engine_transmission;
	driveline.innerText = car.driveLine;
	fuel_type.innerText = car.fuel_type;


	// add <td>'s to our <tr>
	tr.appendChild(edit)
	tr.appendChild(name);
	tr.appendChild(make);
	tr.appendChild(model);
	tr.appendChild(year);
	tr.appendChild(engine_type);
	tr.appendChild(transmission);
	tr.appendChild(engine_transmission);
	tr.appendChild(driveline);
	tr.appendChild(fuel_type);
	tr.appendChild(del)

	// add our <tr> to our <tbody>
	document.getElementById('car-table-body').appendChild(tr);
}


document.getElementById('new-car-form').addEventListener('submit', function(event) {
	event.preventDefault();		// prevent default form actions from occuring

	// get the data from the form
	var name = document.getElementById('modal-name').value;
	var make = document.getElementById('modal-make').value;
	var model = document.getElementById('modal-model').value;
	var year = document.getElementById('modal-year').value;
	var engine_type = document.getElementById('modal-engine').value;
	var transmission = document.getElementById('modal-transmission').value;
	var engine_transmission = document.getElementById('modal-et').value;
	var driveLine = document.getElementById('modal-driveline').value;
	var fuel_type = document.getElementById('modal-fuel').value;

	console.log(name + " " + make + " " + model + " " + year + " " + engine_type + " " + transmission + " "
		+ engine_transmission + " " + driveLine + " " + fuel_type)

	//     ES6+ allows for object literal syntax: basically JSON objects on the fly
	var car = {
		name: name,
		model: model,
		make: make,
		year: year,
		engine_type: engine_type,
		transmission: transmission,
		engine_transmission: engine_transmission,
		driveLine: driveLine,
		fuel_type: fuel_type
	};


	// 1. make an xhr object (ready state is 0)
	let xhr = new XMLHttpRequest();		// make HTTP requests

	// 2. define what happens during the AJAX call
	xhr.onreadystatechange = function() {

		if (xhr.readyState === 4) {

			// getting back the updated movie object
			var updatedCar = JSON.parse(xhr.responseText);

			// adding the updated movie to our table
			addCarToTable(updatedCar);

			// reset the form
			document.getElementById('new-car-form').reset();

			$('#carModal').modal('hide');
		}
	}

	// 3. open the xhr call with the http request verb and the url
	xhr.open('POST', '/car/api/cars');

	// 4. send the ajax call
	xhr.send(JSON.stringify(car));
});


var carModal = document.getElementById('buttonM')
carModal.addEventListener('show.bs.modal', function(event) {
	// Button that triggered the modal
	var button = event.relatedTarget
})


var deleteCar = function(str_id) {
	console.log(str_id);
	let xhr = new XMLHttpRequest();

	xhr.open('DELETE', '/car/api/cars');
	
	// 4. send the ajax call
	xhr.send(JSON.stringify({ id: str_id }));
}


var editCar = function(car) {
	document.getElementById('edit-car-form').addEventListener('submit', function(event) {
		event.preventDefault();		
		// get the data from the form
		var editName = document.getElementById('edit-name').value;
		var editMake = document.getElementById('edit-make').value;
		var editModel = document.getElementById('edit-model').value;
		var editYear = document.getElementById('edit-year').value;
		var editEngine_type = document.getElementById('edit-engine').value;
		var editTransmission = document.getElementById('edit-transmission').value;
		var editEngine_transmission = document.getElementById('edit-et').value;
		var editDriveLine = document.getElementById('edit-driveline').value;
		var editFuel_type = document.getElementById('edit-fuel').value;

if(editName == ''){
	editName = car.name;
}
if(editMake == ''){
	editMake = car.make;
}
if(editModel == ''){
	editModel = car.model;
}
 if(editYear == ''){
	editYear = car.year;
}
if(editEngine_type == ''){
	editEngine_type = car.engine_type;
}
if(editTransmission == '' ){
	editTransmission = car.transmission;
}
if(editEngine_transmission == ''){
	editEngine_transmission = car.engine_transmission;
}
if(editDriveLine == ''){
	editDriveLine = car.driveLine;
}
if(editFuel_type == ''){
	editFuel_type = car.fuel_type
}
		    var updatedCar = {
			    id : car.id,
		        name : editName,
		        model : editModel,
		        make : editMake,
		        year : editYear,
		        engine_type : editEngine_type,
		        transmission : editTransmission,
		        engine_transmission : editEngine_transmission,
		        driveLine : editDriveLine,
		        fuel_type : editFuel_type
		    };

	let xhr = new XMLHttpRequest();		// make HTTP requests

	// 2. define what happens during the AJAX call
	xhr.onreadystatechange = function() {

		if (xhr.readyState === 4) {
			
			document.getElementById('edit-car-form').reset();
            $('#editModal').modal('hide');
            window.location.reload();
		}
	}

	// 3. open the xhr call with the http request verb and the url
	xhr.open('PUT', '/car/api/cars');

	// 4. send the ajax call
	xhr.send(JSON.stringify(updatedCar));
	});
}
