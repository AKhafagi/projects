/* Team Oregon
* Artur Prusinowski, Anas Khafagi, Justin Waymire
* Edoras account: cssc0637
* CS-530 Programming Assignment 3
*
* file_parse_exception.h
* CS530, Fall 2016
*/

#ifndef FILE_PARSE_EXCEPTION_H
#define FILE_PARSE_EXCEPTION_H

#include <string>

using namespace std;

class file_parse_exception {

public:
    file_parse_exception(string s) {
        message = s;
    }

    file_parse_exception() {
        message = "An error has occurred";
    }

    string getMessage() {
        return message;
    }

private:
    string message;
};

#endif
