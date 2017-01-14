/* Team Oregon
 * Artur Prusinowski, Anas Khafagi, Justin Waymire
 * Edoras account: cssc0637
 * CS-530 Programming Assignment 3
 *
 * macroPP.cpp
 * CS530, Fall 2016
*/
#include "macroPP.h"

macroPP::macroPP(string filename) {
    this->filename = filename;
    file_processor = new file_parser(filename);
    file_processor->read_file();
}

int main(int argc, char **argv) {
    if (argc != 2) {
        cout << "Usage error: Put the filename as the only argument" << endl;
        return 1;
    }
    macroPP macro_extractor = macroPP(argv[1]);

    try {
        macro_extractor.process_file(argv[1]);
        macro_extractor.print_file();
        cout << "Macro Expansion Completed Successfuly !!" << endl;
        cout << "Source Code Ready To Be Assembled !! " << endl;
    }
    catch (macroPP_error_exception &e) {
        cout << e.getMessage() << endl;
    }
    return 0;
}

void macroPP::process_file(string current_file_name) {
    file_parser current_file_processor(current_file_name);
    try {
        current_file_processor.read_file();
    }
    catch (file_parse_exception &e) {
        throw macroPP_error_exception(e.getMessage());
    }
    int file_size = current_file_processor.size();
    string macro_name;
    // Cycle through all the lines in the source code.
    for (int i = 0; i < file_size; i++) {
        current_row = current_file_processor.get_line(i);
        macro_name = current_row.opcode.substr(0, current_row.opcode.size() - 2);
        if (is_endm(current_row.opcode))
            throw macroPP_error_exception(
                    " \"endm\" found before \"macro\" invocation on line " + to_string(i) + " of the file " + "\"" +
                    current_file_name + "\"");

        /* if label is #minclude, recursivley call the method with the opcode as the new file name */
        if (is_minclude(current_row.label))
            process_file(current_row.opcode);
            /* if opcode macro, call the method process_macro, to store it in the Data Structure
             * with the current row, and the file_parser
             * @throws if duplicate macro found.
             * */
        else if (is_macro(current_row.opcode)) {
            i++;
            if (file_macros.find(current_row.label) == file_macros.end())
                process_macro(i, &current_file_processor);
            else
				throw macroPP_error_exception(
					"duplicate Macro found on line " + to_string(i) + " of the file " + current_file_name);

        } else if (file_macros.find(current_row.opcode) != file_macros.end() ||
                   file_macros.find(macro_name) != file_macros.end()) {
            comment_macro_call(current_row);
            expand_macro(i);
			row end_macro_expansion;
            row empty_line;
			end_macro_expansion.label = "*\tEND OF MACRO EXPANSION";
            script_final.push_back(empty_line);
			script_final.push_back(end_macro_expansion);
            script_final.push_back(empty_line);

        }
            /* push current row to the final output vector, ignore comments if in external files*/
        else {
            if (current_file_name != filename)
                current_row.comment.clear();
            script_final.push_back(current_row);
        }


    }
}

/*
 * outputs the source code, without the macros, to a new file ending with .se
 * calls the file_parser::row to_string() method, to print the rows in script_final.
 * */
void macroPP::print_file() {
    ofstream out;
    out.open(filename + "e");
    for (row r : script_final) {
        out << r << endl;
    }
    out.close();
}

void macroPP::process_macro(int &row, file_parser *current_file) {
    // Create the temporary macro to accumulate
    macro current_macro;
    if (current_row.label.empty())
        throw macroPP_error_exception(
                "No macro name before macro invocation on line " + to_string(row) + " please check instruction");

    string macro_name = current_row.label; // get macro name.
    current_row = current_file->get_line(row);
    // Cycle through the lines of the macro, adding them to current_macro
    while (!is_endm(current_row.opcode)) {

        /*
         * if macro found inside another macro, recursively call the process_macro method with the
         * current row and the current file_parser object
         * */
        if (is_macro(current_row.opcode)) {
            row++;
            if (file_macros.find(current_row.label) != file_macros.end())
                throw macroPP_error_exception("duplicate Macro found on line " + to_string(row));
            process_macro(row, current_file);
            current_row = current_file->get_line(++row);
        }
        // Remove the comment
        current_row.comment.clear();
        // Add row to current macro
        current_macro.deftab.push_back(current_row);
        // Continue to iterate through macro
        row++;
        if (row == current_file->size())
            throw macroPP_error_exception("Macro <" + macro_name + "> missing 'endm' command");
        current_row = current_file->get_line(row);
    }
    // maps the current macro to the map of macros by its name.
    file_macros[macro_name] = current_macro;
}

//Prints all macros to the screen.
void macroPP::print_macros() {
    for (auto const &m : file_macros) {
        cout << "Name: " + m.first + "\t\t\t";
        macro macro = m.second;
        cout << macro << endl;
    }

}

void macroPP::replace_ctr(struct row &output, macro *macro1) {
    string::size_type label_size, opcode_size, operand_size;
    label_size = output.label.size();
    opcode_size = output.opcode.size();
    operand_size = output.operand.size();
    if (output.label[label_size - 2] == '@')
        output.label.replace(label_size - 3, 2, to_string(macro1->invocation_ctr));
    if (output.opcode[opcode_size - 1] == '0')
        output.opcode.replace((opcode_size - 2), 2, macro1->paramtab[0]);
    if (output.operand[operand_size - 1] == '@')
        output.operand.replace(operand_size - 2, 2, to_string(macro1->invocation_ctr));

}

void macroPP::replace_param(struct row &output, macro *macro1, int index) {
    string parameter;
    int num_parameter = 0;
    while (index != NOT_FOUND) {
        parameter = output.operand[index + 1];
        num_parameter = atoi(parameter.c_str());
        if (num_parameter > macro1->paramtab.size() - 1) {
            throw macroPP_error_exception("Parameter mismatch please check arguments for macro " + current_row.opcode);

        }
        output.operand.replace(index, 2, macro1->paramtab[num_parameter]);
        index = output.operand.find("\\", index);

    }
    script_final.push_back(output);
}


void macroPP::expand_instructions(macro *macro1) {
    row output;
    string::size_type index;
    for (row curr_row : macro1->deftab) {
        output = curr_row;
        replace_ctr(output, macro1);
        index = output.operand.find("\\", 0);
        replace_param(output, macro1, index);
    }
    macro1->paramtab.clear();
    macro1->invocation_ctr++;

}


void macroPP::expand_macro(int &row_num) {
    string opcode = file_processor->get_token(row_num, 1);
    if (opcode.find('.') != NOT_FOUND)
        opcode = opcode.substr(0, opcode.size() - 2);
    macro *macro1 = &file_macros[opcode];
    get_parameters_size(macro1, current_row);
    expand_instructions(macro1);
}


vector<string> macroPP::get_parameters(string str) {
    const string &delimiters = ",";
    vector<string> parameters;
    string::size_type last_pos = str.find_first_not_of(delimiters, 0); // Skip delimiters at beginning.
    string::size_type pos = str.find_first_of(delimiters, last_pos); // Find first "delimiter".
    string parameter;
    while (pos != NOT_FOUND || last_pos != NOT_FOUND) {
        parameter = str.substr(last_pos, pos - last_pos);
        parameters.push_back(parameter);
        last_pos = str.find_first_not_of(delimiters, pos);
        pos = str.find_first_of(delimiters, last_pos);
    }
    return parameters;
}

string macroPP::get_instruction_size(string opcode) {
    // Find first "delimiter".
    if (opcode.find_first_of(".", 0) != NOT_FOUND)
        return &opcode[opcode.size() - 1];
    else
        return "w";

}

void macroPP::get_parameters_size(macro *macro1, row current_row) {
    macro1->paramtab = get_parameters(current_row.operand);
    macro1->paramtab.insert(macro1->paramtab.begin(), get_instruction_size(current_row.opcode));

}

void macroPP::comment_macro_call(struct row row1) {
    if (row1.label.empty())
        row1.comment = "*" + row1.label + "\t" + row1.opcode + "\t" + row1.operand + "\t" + row1.comment;
    else {
        row1.comment = "*" + row1.opcode + "\t" + row1.operand + "\t" + row1.comment;
    }
    row1.operand.clear();
    row1.opcode.clear();
    script_final.push_back(row1);
}


