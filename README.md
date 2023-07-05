# text-merge
Text Merging Web App

## Abstract
This project aims to identify similarities and frequency-based features between sentences/words obtained from texts such as books and magazines, decide whether the texts should be merged or not, and merge character-based sentences/words based on the obtained data. The main objective of the project is to develop new algorithms for the problem of merging two or more texts and present them with a visual interface.

## Introduction
Within the scope of the project, at least two text input buttons, a merge button for combining entered texts, a save button for saving entered texts in JSON format, and the entire list of longest sequences obtained from the data need to be saved as a list. Additionally, the elapsed time for the merging process of two texts will be calculated.

Java programming language will be used in this project, and MongoDB (NoSQL) will be used as the database. Any desired programming language can be used for the web interface.

To allow users to save and list the entered texts, the entered texts should be stored as documents in NoSQL, in JSON format.

The visual interface will include two text input labels and "add," "merge," and "save" buttons associated with these labels. Users can easily enter, merge, and save texts using these buttons.

## Method
The relevant project consists of a database, frontend, and backend parts.

Firstly, a Java Spring project should be created using a development environment since a web project is desired. This way, the web application can run at localhost:8080 without the need for an additional web server.

For the MongoDB database connection in the created project, the MongoDB Desktop application is downloaded to the local machine, and the necessary connection strings are obtained from the application. A DatabaseConnection class is created in the code, where the relevant connection codes are written.

After this stage, the frontend development of the project is carried out. It is preferred to perform the necessary operations within an HTML document for this part. The appearance settings of the components to be displayed in the interface, background images, and the actions to be taken when interacting with the buttons in the form are defined in the HTML document.

Finally, the backend development process is carried out. In this stage, if two texts are entered from the interface and optionally an additional text is entered, they are merged using the AddText button. When the Merge Text button is used, the entered texts are merged using the algorithms shared in the Flow Diagram section. The execution time of the text merging algorithm is calculated.

In the MergeSimilarText class, the confirmed similar texts are merged by removing common words. In the SimilarityData class, the similarity score between texts and which texts the similarities are between are stored. In the TextMergeManager class, the process is managed using functions from other classes, and in the TextSimilarity class, the modified versions of CosineSimilarity, JaccardSimilarity, and a modified version of CosineSimilarity that considers the first 3 letters are used to convert the similarity between texts into a numerical score. Based on the score, it is determined whether the texts are similar or not.

## Results
As a result of all the operations performed, a connection to MongoDB was established. The texts obtained from the interface are displayed in a textbox on the screen. These texts are merged properly by calculating their similarities. The merged text is saved in the MongoDB database in JSON format. The duration of the merging process is calculated and displayed in the interface.

The relevant project has been successfully tested using Windows 11 operating system, IntelliJ IDEA 2022
