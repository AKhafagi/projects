/* Team Oregon
* Artur Prusinowski, Anas Khafagi, Justin Waymire
* Edoras account: cssc0637
* CS-530 Programming Assignment 3
*
* macroPP_error_exception.h
* CS530, Fall 2016
*/

#ifndef MACROPP_ERROR_EXCEPTION_H
#define MACROPP_ERROR_EXCEPTION_H

#include <string>

using namespace std;

class macroPP_error_exception {

public:
    macroPP_error_exception(string s) {
        message = s;
    }

    macroPP_error_exception() {
        message = "An error has occurred";
    }

    string getMessage() {
        return message;
    }

private:
    string message;
};

#endif
