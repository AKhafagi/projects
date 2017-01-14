/* Team Oregon
* Artur Prusinowski, Anas Khafagi, Justin Waymire
* Edoras account: cssc0637
* CS-530 Programming Assignment 3
*
* file_parser.h
* CS530, Fall 2016
*/

#ifndef FILE_PARSER_H
#define FILE_PARSER_H

#include <string>
#include <vector>
#include <iostream>     // std::cout
#include <fstream>      // std::ifstream
#include <cstddef>      //size_t
#include "file_parse_exception.h"
#include <iomanip>

#define NOT_FOUND      string::npos
#define DELIMITER      " \n\r\t"
#define SINGLE_QUOTE   "\'"
#define MAX_COLUMNS     4
#define LABEL           0
#define OPCODE          1
#define IS_COMMENT(C)                (C == '*' || C == ';')
#define CHOP_FRONT_AND_BACK(S)        (S = S.substr(1, S.size() - 2))
#define CHOP_FRONT(S)                (S.substr(1))
#define CHOP_MACRO(S)                (S.substr(0,S.size() - 3))

using namespace std;

struct row {
    string label;
    string opcode;
    string operand;
    string comment;

    void set_option(int col, string value) {
        switch (col) {
            case 0:
                label = value;
                break;
            case 1:
                opcode = value;
                break;
            case 2:
                operand = value;
                break;
            case 3:
                comment = value;
                break;
            default:
                break;
        }
    }

    string get_option(int col) {
        switch (col) {
            case 0:
                return label;
            case 1:
                return opcode;
            case 2:
                return operand;
            case 3:
                return comment;
            default:
                return "";
        }
    }

    friend ostream &operator<<(ostream &os, const row &row1) {
        string output;
        if (row1.label.empty() && row1.opcode.empty() && row1.operand.empty() && !row1.comment.empty())
            output.append(row1.comment);
        else if (row1.label.empty())
            output.append("\t\t" + row1.opcode + "\t\t" + row1.operand + "\t" + row1.comment);
        else
            output.append(row1.label + "\t\t" + row1.opcode + "\t\t" + row1.operand + "\t" + row1.comment);
        os << output;
        return os;
    }

};

typedef vector<row> script;

typedef string::size_type pos_index;

class file_parser {
public:
    /**
    * Creates a file parser object using the file name submitted.
    * @param filename - string containing the name of the source file to be tokenized
    * @return - file_parser object
    */
    file_parser(string);

    /**
     * Destructor
     * */
    ~file_parser() {};

    /**
    * reads the source file, storing the information is some
    * auxiliary data structure you define in the private section.
    * Throws a file_parse_exception if an error occurs.
    * if the source code file fails to conform to the above
    * specification, this is an error condition.
    */
    void read_file();

    /**
    * returns the token found at (row, column).  Rows and columns
    * are zero based.  Returns the empty string "" if there is no
    * token at that location. column refers to the four fields
    * identified above.
    * @param row - Row of the input source file (0 based)
    * @param col - Column from the input row. One of the 0 - 3 columns representing Label,
    * @return  - token string from row, col
    */
    string get_token(unsigned int, unsigned int);

    /**
    * prints the source code file to stdout.
    */
    void print_file();

    /**
    * prints the submitted code file to stdout.  
    */
    void print_file(script);

    /***
     * @return  returns the number of lines in the source code file
     */
    int size() { return file_contents.size(); };

    /**
    * Get a line for external processing
    **/
    row get_line(int index);

private:
    /**source file file name*/
    string filename;

    /**Line to be parsed*/
    vector<string> file_to_parse;

    /**Vector stores all tokens after call to tokenize_lines().
     * Size = number of lines in filename */
    script file_contents;

    /** Current row
    row current_row;**/

    /**
    * Function tokenizes and stores all original source code lines
    * @param file_contents - vector containing string objects each representing one line of unparsed text
     */
    void tokenize_lines();

    /**
    * Updates the two string indexes to point to the start and end of the next token on the line
    * @param row - string containing one complete source file row
    * @param head_pos - points to the last char in the next token to be processed
    * @param tail_pos - points to the first char in the next token to be processed
    */
    void find_next_token(const string &, pos_index &, pos_index &);

    /**
    * Function finds and returns the next complete token from the input line
    * @param row - string of the entire row from source file being tokenized
    * @param head_pos -  index position of the end of token
    * @param tail_pos - index position of the start of token
    * @return  - string consisting of the fully assembled token
    */
    string get_next_token(const string &, pos_index &, pos_index &);

    /**
    *  Check a string for proper LABEL format
    * @param s - string being checked for proper LABEL format
    * @return - true if string s follows proper LABEL format, false otherwise
    */
    bool is_valid_label(string);
};

#endif


