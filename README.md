# BookDatabase 📚

**BookDatabase** is a **JavaFX application** for managing book records using **Apache Derby** as the database. The application allows users to add, view, update, delete, and filter book records through an intuitive graphical interface.

---

## 🚀 Features
- **Add Book Records**: Input details like title, author, year published, price, and bestseller status.
- **View All Records**: Display all books in a **TableView** with sortable columns.
- **Filter Bestselling Books**: Dynamically view only the bestsellers.
- **Update Records**: Handle duplicate book titles by updating existing records.
- **Delete Specific Records**: Remove a book by entering its title.
- **Delete All Records**: Clear the entire database with a single click.
- **Validation**: Ensures correct and complete input data.

- ## 📸 Screenshots

### Main Form Interface
![Main Form][(img/screenshot1.png](https://github.com/rchatthuska/BookDataBase/blob/master/Screenshot%202024-12-17%20180745.png))



---

## 📂 Project Structure
FinalProjectBase/src/main/java/com/example/finalprojectbase/BookDatabase.java
---

## 🛠️ Technologies Used
- **JavaFX**: For graphical user interface.
- **Apache Derby**: Embedded database for data storage.
- **JDK 8+**: Required to run the application.

---

## 📦 Prerequisites
1. **Java Development Kit (JDK)** version **8 or above**.
2. **JavaFX SDK** (Ensure your IDE or CLI supports JavaFX libraries).

---

## 🧩 Setup Instructions

### Option 1: Run in an IDE (IntelliJ, Eclipse, NetBeans)
1. Clone this repository:
   ```bash
   git clone https://github.com/rchatthuska/BookDataBase.git
   cd BookDataBase
Open the project in your IDE.
Add JavaFX library support:
IntelliJ: Go to File > Project Structure > Libraries and add the JavaFX SDK.
Eclipse: Configure JavaFX SDK under Project Properties.
Run the BookDatabase.java file:
css

src/main/resources/com/example/finalprojectbase/BookDatabase.java
Option 2: Run from Command Line
Clone the repository:


git clone https://github.com/rchatthuska/BookDataBase.git
cd BookDataBase/src/main/resources/com/example/finalprojectbase
Compile the program:


javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls BookDatabase.java
Run the application:


java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls com.example.finalprojectbase.BookDatabase
Replace /path/to/javafx-sdk/lib with the correct path to your JavaFX SDK.

🎮 How to Use the Application
Add a Record:

Enter the book's title, author, year published, price, and select bestseller status.
Click "Submit Record".
View All Records:

Click "View Table" to see all book records.
Filter Bestselling Books:

Click "Filter Bestselling Books" to view only the bestsellers.
Delete a Record:

Enter the book title in "Enter Title to Delete" and click "Delete Record".
Delete All Records:

Click "Delete All Records" to clear the database.
Update Records:

If a duplicate title is entered, a prompt will appear to allow you to update the existing record.
📌 Future Improvements
Add export/import functionality to/from CSV or Excel.
Implement a search feature to find books quickly.
Add a high-score feature for most expensive or oldest books.
🧑‍💻 Author
Rumesh Chathuska

GitHub: rchatthuska
📜 License
This project is licensed under the MIT License. See the LICENSE file for details.

Enjoy managing your book database! 📚✨



---

### Steps to Add This README.md:
1. Copy the content above.
2. Go to your repository on GitHub: [BookDatabase](https://github.com/rchatthuska/BookDataBase).
3. Click **"Add file" > "Create new file"**.
4. Name it `README.md` and paste the content.
5. Commit the file by clicking **"Commit new file"**.

---

