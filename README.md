
# AssemblyAI API to translate speech to text

A brief description of how we can call a REST API in java using [AssemblyAI](https://www.assemblyai.com).


## Documentation

#### What exactly is API?

API stands for "application program interface". All an API really is, is a way for two programs to talk to eachother. 

#### Web Service: 
It can handle HTTP requests that are sent to it, do some work and then return response.
These web services are called REST services or REST full services. (Representational State Transfer).

#### HTTPRequest in Java:
All the elements of our request(url, http method<GET, POST, ...>, request body, headers) are going to get put into one object called HTTPRequest.
## Run Locally

Clone the project

```bash
  git clone https://github.com/hamid842/java-audio-to-text
```

and run the main method.


## Environment Variables

To run this project, you will need to add the following environment variables to your .env file or set into intellyJ run configuration dialog.

`API_KEY`

You can get the `API_KEY` [here](https://www.assemblyai.com) after you logged in.

