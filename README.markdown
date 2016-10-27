                                #This is a webservice exercise to show RESTful web development.
------------------------------------------------------------------------------------------------------------------------

    To start this project you will need to pull this project onto your local machine from GIT. Once you have done this,
you can start the project by opening a terminal window in the directory of your pulled repo and typing:

                                             c:\nikedeck> gradlew jettyRunWar

    This command automatically cleans and builds your project before starting the Jetty server. Once this is done, you
can open an internet explorer window to one of the endpoints that are on your Jetty server to make sure your project has
been set up correctly. A good starting endpoint to check is the getAllDecks endpoint:

                                            http:/localhost:8080/nikedeck/decks

    This should give a blank page with a string saying you have called the getDecks GET request and will deliver a list
of deck names. You can check the endpoints from this page, but I suggest that you use the "Postman" application to check
all request types. This application can be installed on Windows or Mac, but for windows you will need to have installed
Chrome as well. The following is the link to this application: [link]https://www.getpostman.com/.

    Using this tool, you should be able to interact with all of the HTTP requests that are created in this project. If
you do not know how to use this tool. Please contact me and I will help you through it. Otherwise, I would suggest you
use whatever application you are most comfortable with to check the HTTP requests in this project.

    To do automated testing, all you will need to do it clean and build the project. It should generate html under
nikedeck/build/reports/tests/index.html that you can open and see the results of your tests. Currently the test portion
of this code is not operational. I have attempted to create them, but am currently blocked until I find out the
proper dependencies to use. In their stead, their are placeholder strings along with some pseudo-code as to the steps
that should be taken to determine if the requests are working as needed.