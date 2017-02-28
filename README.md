# shopmanage
# How to import the project
- Take all the project from git hub using following command 
	git clone https://github.com/sandepgarg/shopmanage.git

- Go to the project path and run following command
	gradle clean build eclipse

- Import the project in eclipse/ Spring Tool Suite

Shop APIs
*****************************

- Add a new shop
	method	: POST
 	URL 	: http://localhost:8080/shopmanage/shops
	body 	: 	{
					"id":"1",
					"shopName":"My Shop 1",
					"shopAddress":{
						"number":"Hisar",
						"postCode":"125001"
					}
				}
	headers:	
		Content-Type:application/json or application/vnd.sg.sm.shop+json
	API validation :
		id of the shop should be unique 
		
- View list of all the shop
	method	: GET
	URL 	: http://localhost:8080/shopmanage/shops
	Path variables : use to get the shop near a location 
			longitude
			latitude
		example http://localhost:8080/shopmanage/shops?longitude=18.501999&latitude=73.928627

- View a specific shop
	method 	: GET
	URL 	: http://localhost:8080/shopmanage/shops/{shopId}
