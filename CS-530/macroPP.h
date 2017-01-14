/* Team Oregon
* Artur Prusinowski, Anas Khafagi, Justin Waymire
* Edoras account: cssc0637
* CS-530 Programming Assignment 3
*
* macroPP.h
* CS530, Fall 2016
*/

#ifndef MACROPP_H
#define MACROPP_H

#include <string>
#include <unordered_map>
#include <vector>
#include <iostream>     // std::cout
#include <fstream>      // std::ifstream
#include <cstddef>      //size_t
#include "file_parser.h"
#include "macroPP_error_exception.h"

using namespace std;

/*
 * A Struct Macro to hold all the fields of a macro.
 * fields:
 * 	invocatation counter : the number of calls to a macro.
 *  deftab : the instructions of the macro.
 *  paramtab : the parameters of the macro call.
 */
struct macro {
    int invocation_ctr = 0;
    vector<row> deftab;
    vector<string> paramtab;

    /*
     * Prints the macro to the screen.
     */
    friend ostream &operator<<(ostream &os, const macro &macro1) {
        os << "Invocation Counter: " << macro1.invocation_ctr << endl;
        // prints the parameters of the macro
        os << "Parameters:" << endl;
        for (string str : macro1.paramtab) {
            os << str + "\t";
        }
        os << "Instructions:" << endl;
        for (row r : macro1.deftab) {
            os << r << endl;
        }
        os << endl << endl;
        return os;
    }
};

/*
 * A Data Structure Map to store the macros in.
 * Key: Name of the macro.
 * Value: A macro struct that holds all the fields of the macro.
 */
typedef unordered_map<string, macro> macros;

class macroPP {
public:
    /**
    * Reads file in and parses it removing macros and stores them in map @name file_macros.
    * @param filename - string containing the name of the source code file to be processed
    * @return - MacroPP object
    */
    macroPP(string);

    /**
     * Destructor, deletes the dynamically allocated @name file_processor.
     */
    ~macroPP() { delete file_processor; };

    /**
    * processes the source code file, storing the code, without the macros, in the
    * vector of rows @name script_final Data Structure and the macros in the map @name file_macros .
    * @param file_name the name of the file to process.
    * @throws macroPP_error_exception if an error occurs.
    */
    void process_file(string);

    /**
    * prints the macros to the screen
    * @calls print_file
    */
    void print_macros();

    /** prints the original source code, without the macros, to a new file ending with .se.*/
    void print_file();

    /** returns the number of macros in the Data Structure
     * @return number of macros in the map macros.
     */
    int number_macros() { return file_macros.size(); }


private:

    /** Source code file name*/
    string filename;

    /** file_parser object to parse the source code **/
    file_parser *file_processor;

    /**
     * processes the source code file.
     * stores the macros in the map @name macros.
     * stores the original source code, without the macros, in vector @name script_final.
     * @param int row: the row of the macro invocation.
     * @param file_parser* current_file: the file_parser to parse the file.
     * **/
    void process_macro(int &, file_parser *);

    /** expands the macro instructions and replaces all the parameters */
    void expand_macro(int &);

    /** The current row under analysis **/
    row current_row;

    /** original source code without the macros **/
    script script_final;

    /** @typedef macros : a map of macros*/
    macros file_macros;

    /**
     * Checks if the string is a #minclude CaseInsensitive.
     * @return true if string is #minclude, false otherwise
     * */
    bool is_minclude(string str) { return str == "#minclude" || str == "#MINCLUDE"; }

    /**
     * Checks if the string is a macro CaseInsensitive.
     * @return true if string is macro, false otherwise
     * */
    bool is_macro(string str) { return str == "macro" || str == "MACRO"; }

    /**
     * Checks if the string is a endm CaseInsensitive.
     * @return true if string is endm, false otherwise
     * */
    bool is_endm(string str) { return str == "endm" || str == "ENDM"; }

    /**
    * parses the parameters of a macro call into a vector.
    * @return a vector<string> containing the parameters of the macro call
    * */
    vector<string> get_parameters(string basic_string);

    /**
    * returns the instruction size of the macro call
    * @return the instruction size of the macro if defined or a "w" in all other cases
    * */
    string get_instruction_size(string basic_string);


    /** comments out the macro call for printing to the file */
    void comment_macro_call(row);


    /** adds the parameters and the instruction size to the Paramtab of the macro * */
    void get_parameters_size(macro *, row);


    /** expands the instructions of the macro line by line. */
    void expand_instructions(macro *macro1);

    /** replaces the invocation counter in all the labels for subsequent calls of the macro*/
    void replace_ctr(row &, macro *);

    /** replaces the place holders in the macro defintion with the macro parameters procvided with the macro call*/
    void replace_param(row &output, macro *macro1, int index);
};

#endif


