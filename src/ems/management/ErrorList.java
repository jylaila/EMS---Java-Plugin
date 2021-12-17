/**
 * Esta classe utiliza Hastables para determinar a pasta para cada erro, assim como recuperar o nome 
 * do erro a partir da pasta
 * @author Maria Janiana da S Ferreira
 */
package ems.management;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ErrorList implements Comparable<ErrorList> {

	private Hashtable<String, String> errorDir;
	public ArrayList<String> keys;
	public String path, errorName;

	/**
	 * Método para criação da tabela de erros
	 */
	public void doErrorList() {

		errorDir = new Hashtable<String, String>();

		// Insert errors in table
		errorDir.put("abstract cannot preced method signature in interfaces", "abstract_cannot_preced_method_signature_in_interfaces");
		errorDir.put("abstract cast method", "abstract_cast_method");
		errorDir.put("abstract method declared with final", "abstract_method_declared_with_final");
		errorDir.put("abstract method in a non abstract prototype", "abstract_method_in_a_non_abstract_prototype"); 
		errorDir.put("abstract methods cannot be declared in interfaces", "abstract_methods_cannot_be_declared_in_interfaces");
		errorDir.put("attempt to assign a value to a constant", "attempt_to_assign_a_value_to_a_constant");
		errorDir.put("attempt to extend a final type", "attempt_to_extend_a_final_type");
		errorDir.put("backquote not followed by a string variable", "backquote_not_followed_by_a_string_variable");
		errorDir.put("both init and new methods", "both_init_and_new_methods");
		errorDir.put("colon expected", "colon_expected");
		errorDir.put("constant expected outside method", "constant_expected_outside_method");
		errorDir.put("cyan metaobject error message inside method", "cyan_metaobject_error_message_inside_method");
		errorDir.put("cyan metaobject error message inside prototype", "cyan_metaobject_error_message_inside_prototype");
		errorDir.put("cyan metaobject error message outside prototype", "cyan_metaobject_error_message_outside_prototype");
		errorDir.put("cyan prototype expected as type", "cyan_prototype_expected_as_type");
		errorDir.put("dot square backet expected", "dot_square_backet_expected");
		errorDir.put("duplicate method", "duplicate_method");
		errorDir.put("empty literal array", "empty_literal_array");
		errorDir.put("empty literal tuple", "empty_literal_tuple");
		errorDir.put("error in literal object", "error_in_literal_object");
		errorDir.put("error in macro", "error_in_macro");
		errorDir.put("expression cannot be indexed by this index inside method", "expression_cannot_be_indexed_by_this_index_inside_method");
		errorDir.put("expression cannot be indexed by this index outside method", "expression_cannot_be_indexed_by_this_index_outside_method");
		errorDir.put("expression expected inside method", "expression_expected_inside_method");
		errorDir.put("file does not exist", "file_does_not_exist");
		errorDir.put("file does not have cyan extension", "file_does_not_have_cyan_extension");
		errorDir.put("file error", "file_error");
		errorDir.put("file name incorrect in compilation unit", "file_name_incorrect_in_compilation_unit");
		errorDir.put("file should be directory", "file_should_be_directory");
		errorDir.put("final cast method", "final_cast_method");
		errorDir.put("formal parameter starting with lower case letter", "formal_parameter_starting_with_lower_case_letter");
		errorDir.put("function returning values of different types", "function_returning_values_of_different_types");
		errorDir.put("generic parameter expected", "generic_parameter_expected");
		errorDir.put("identifier cannot be used in the left hand side of an assignment", "identifier_cannot_be_used_in_the_left_hand_side_of_an_assignment");
		errorDir.put("identifier expected inside method", "identifier_expected_inside_method");
		errorDir.put("identifier expected outside prototype", "identifier_expected_outside_prototype"); 
		errorDir.put("identifier was not declared inside method", "identifier_was_not_declared_inside_method");
		errorDir.put("identifier was not declared outside method", "identifier_was_not_declared_outside_method");
		errorDir.put("identifier was not declared", "identifier_was_not_declared"); 
		errorDir.put("illegal use of backquote", "illegal_use_of_backquote");
		errorDir.put("incompatible return type in subprototype method", "incompatible_return_type_in_subprototype_method");
		errorDir.put("indexing method was not found inside method", "indexing_method_was_not_found_inside_method");
		errorDir.put("indexing method was not found outside method", "indexing_method_was_not_found_outside_method");
		errorDir.put("init new cannot be preceded by indexing operator", "init_new_cannot_be_preceded_by_indexing_operator");
		errorDir.put("init new methods cannot be declared in interfaces", "init_new_methods_cannot_be_declared_in_interfaces");
		errorDir.put("init new should be public", "init_new_should_be_public");
		errorDir.put("init new should not appear in grammar method", "init_new_should_not_appear_in_grammar_method");
		errorDir.put("init should not be abstract", "init_should_not_be_abstract");
		errorDir.put("init should not be declared with override", "init_should_not_be_declared_with_override");
		errorDir.put("init should not be final", "init_should_not_be_final");
		errorDir.put("init should return Nil", "init_should_return_Nil");
		errorDir.put("instance variable is not visible here", "instance_variable_is_not_visible_here");
		errorDir.put("interface name expected", "interface_name_expected");
		errorDir.put("internal error", "internal_error");
		errorDir.put("keyword end expected", "keyword_end_expected");
		errorDir.put("keyword object expected", "keyword_object_expected");
		errorDir.put("keyword package expected", "keyword_package_expected");
		errorDir.put("keyword program expected", "keyword_program_expected");
		errorDir.put("literal string expected after comma", "literal_string_expected_after_comma");
		errorDir.put("literal string expected", "literal_string_expected");
		errorDir.put("local variable is being redeclared", "local_variable_is_being_redeclared");
		errorDir.put("metaobject attempt to add two methods with the same name to a prototype", "metaobject_attempt_to_add_two_methods_with_the_same_name_to_a_prototype");
		errorDir.put("metaobject call error missing args symbols", "metaobject_call_error_missing_args_symbols");
		errorDir.put("metaobject cannot be attached to prototype", "metaobject_cannot_be_attached_to_prototype");
		errorDir.put("metaobject error", "metaobject_error");
		errorDir.put("metaobject was not found inside method", "metaobject_was_not_found_inside_method");
		errorDir.put("metaobject was not found outside method", "metaobject_was_not_found_outside_method");
		errorDir.put("metaobject was not found outside prototype", "metaobject_was_not_found_outside_prototype");
		errorDir.put("metaobject wrong number of parameters", "metaobject_wrong_number_of_parameters");
		errorDir.put("method cannot be indexing method", "method_cannot_be_indexing_method");
		errorDir.put("method cannot be user defined", "method_cannot_be_user_defined");
		errorDir.put("method is duplicated", "method_is_duplicated");
		errorDir.put("method is not overriding any super type method", "method_is_not_overriding_any_super_type_method");
		errorDir.put("method is not visible here", "method_is_not_visible_here");
		errorDir.put("method is overridden method with different visibility", "method_is_overridden_method_with_different_visibility");
		errorDir.put("method should be declared after previous method with the same selectors", "method_should_be_declared_after_previous_method_with_the_same_selectors");
		errorDir.put("method was not found in current prototype or super prototypes", "method_was_not_found_in_current_prototype_or_super_prototypes");
		errorDir.put("method was not found in prototype or super prototypes", "method_was_not_found_in_prototype_or_super_prototypes");
		errorDir.put("methods with the same selectors and different return types", "   	methods_with_the_same_selectors_and_different_return_types");   
		errorDir.put("methods with the same selectors and different visibilities", "methods_with_the_same_selectors_and_different_visibilities");
		errorDir.put("methods with the same selectors with and without final", "   	methods_with_the_same_selectors_with_and_without_final");   
		errorDir.put("methods with the same selectors with and without override", "methods_with_the_same_selectors_with_and_without_override");
		errorDir.put("mixing of different parameter kinds in generic prototype", "mixing_of_different_parameter_kinds_in_generic_prototype");
		errorDir.put("mixins of generic and non generic parameters", "mixins_of_generic_and_non_generic_parameters");
		errorDir.put("more than one formal plus parameter in generic prototype", "more_than_one_formal_plus_parameter_in_generic_prototype"); 
		errorDir.put("new cannot be abstract", "new_cannot_be_abstract");
		errorDir.put("new cannot be declared with override", "new_cannot_be_declared_with_override");
		errorDir.put("new cannot be final", "new_cannot_be_final");
		errorDir.put("new methods should be public", "new_methods_should_be_public");
		errorDir.put("new with illegal return type","new_with_illegal_return_type");
		errorDir.put("no public protected prototype found in source file", "no_public_protected_prototype_found_in_source_file");
		errorDir.put("non generic prototype in the same source file with generic prototype", "non_generic_prototype_in_the_same_source_file_with_generic_prototype");
		errorDir.put("non public cast method", "non_public_cast_method");
		errorDir.put("non public generic prototype", "non_public_generic_prototype");
		errorDir.put("override cannot preced method signature in interfaces", "override_cannot_preced_method_signature_in_interfaces");
		errorDir.put("override without supertype", "override_without_supertype");
		errorDir.put("package has a wrong name", "package_has_a_wrong_name");
		errorDir.put("package is importing itself", "package_is_importing_itself");
		errorDir.put("package name expected", "package_name_expected");
		errorDir.put("package was not found inside method", "package_was_not_found_inside_method");
		errorDir.put("package was not found outside prototype", "package_was_not_found_outside_prototype");
		errorDir.put("parameter is being redeclared", "parameter_is_being_redeclared");
		errorDir.put("private method declared with abstract", "private_method_declared_with_abstract");
		errorDir.put("private method declared with final", "private_method_declared_with_final");
		errorDir.put("private method declared with override", "private_method_declared_with_override");
		errorDir.put("prototype as type expected inside method", "prototype_as_type_expected_inside_method");
		errorDir.put("prototype cannot be used in the left hand side of an assignment", "prototype_cannot_be_used_in_the_left_hand_side_of_an_assignment");
		errorDir.put("prototype cannot inherit from an interface", "prototype_cannot_inherit_from_an_interface");
		errorDir.put("prototype imported from two or more packages inside method", "prototype_imported_from_two_or_more_packages_inside_method");
		errorDir.put("prototype was not found inside method", "prototype_was_not_found_inside_method");
		errorDir.put("prototype was not found inside prototyped", "prototype_was_not_found_inside_prototyped");
		errorDir.put("prototype was not found outside prototype", "prototype_was_not_found_outside_prototype");
		errorDir.put("qualifier cannot preced method signature in interfaces", "qualifier_cannot_preced_method_signature_in_interfaces");
		errorDir.put("real parameter of generic prototype expected", "real_parameter_of_generic_prototype_expected");
		errorDir.put("return with caret outside a function", "return_with_caret_outside_a_function");
		errorDir.put("right parenthesis expected in mixin declaration", "right_parenthesis_expected_in_mixin_declaration");
		errorDir.put("right square bracket expected in indexing method", "right_square_bracket_expected_in_indexing_method"); 
		errorDir.put("semicolon expected", "semicolon_expected");
		errorDir.put("tuple field name expected", "tuple_field_name_expected");
		errorDir.put("two or more backquotes in unary chain", "two_or_more_backquotes_in_unary_chain");
		errorDir.put("two or more generic prototype in the same source file", "two_or_more_generic_prototype_in_the_same_source_file");
		errorDir.put("two or more init methods", "two_or_more_init_methods");
		errorDir.put("two or more public protected prototype found in source file", "two_or_more_public_protected_prototype_found_in_source_file");
		errorDir.put("type error return value type is not a subtype of the function return type", "type_error_return_value_type_is_not_a_subtype_of_the_function_return_type");
		errorDir.put("type error return value type is not a subtype of the method return type", "type_error_return_value_type_is_not_a_subtype_of_the_method_return_type");
		errorDir.put("type error type of right hand side of assignment is not a subtype of the type of left hand side", "type_error_type_of_right_hand_side_of_assignment_is_not_a_subtype_of_the_type_of_left_hand_side");
		errorDir.put("typeof used in union", "typeof_used_in_union");
		errorDir.put("unary method cannot be used in the left hand side of an assignment", "unary_method_cannot_be_used_in_the_left_hand_side_of_an_assignment");
		errorDir.put("use of super without a super prototype", "use_of_super_without_a_super_prototype");
		errorDir.put("variable was not declared", "variable_was_not_declared");

	}

	/**
	 * Método para criação das pastas
	 */
	public void doFolderList() {

		errorDir = new Hashtable<String, String>();		

		// Insert errors in table
		errorDir.put("abstract_cannot_preced_method_signature_in_interfaces", "abstract cannot preced method signature in interfaces");
		errorDir.put("abstract_cast_method", "abstract cast method");
		errorDir.put("abstract_method_declared_with_final", "abstract method declared with final");
		errorDir.put("abstract_method_in_a_non_abstract_prototype", "abstract method in a non abstract prototype"); 
		errorDir.put("abstract_methods_cannot_be_declared_in_interfaces", "abstract methods cannot be declared in interfaces");
		errorDir.put("attempt_to_assign_a_value_to_a_constant", "attempt to assign a value to a constant");
		errorDir.put("attempt_to_extend_a_final_type", "attempt to extend a final type");
		errorDir.put("backquote_not_followed_by_a_string_variable", "backquote not followed by a string variable");
		errorDir.put("both_init_and_new_methods", "both init and new methods");
		errorDir.put("colon_expected", "colon expected");
		errorDir.put("constant_expected_outside_method", "constant expected outside method");
		errorDir.put("cyan_metaobject_error_message_inside_method", "cyan metaobject error message inside method");
		errorDir.put("cyan_metaobject_error_message_inside_prototype", "cyan metaobject error message inside prototype");
		errorDir.put("cyan_metaobject_error_message_outside_prototype", "cyan metaobject error message outside prototype");
		errorDir.put("cyan_prototype_expected_as_type", "cyan prototype expected as type");
		errorDir.put("dot_square_backet_expected", "dot square backet expected");
		errorDir.put("duplicate_method", "duplicate method");
		errorDir.put("empty_literal_array", "empty literal array");
		errorDir.put("empty_literal_tuple", "empty literal tuple");
		errorDir.put("error_in_literal_object", "error in literal object");
		errorDir.put("error_in_macro", "error in macro");
		errorDir.put("expression_cannot_be_indexed_by_this_index_inside_method", "expression cannot be indexed by this index inside method");
		errorDir.put("expression_cannot_be_indexed_by_this_index_outside_method", "expression cannot be indexed by this index outside method");
		errorDir.put("expression_expected_inside_method", "expression expected inside method");
		errorDir.put("file_does_not_exist", "file does not exist");
		errorDir.put("file_does_not_have_cyan_extension", "file does not have cyan extension");
		errorDir.put("file_error", "file error");
		errorDir.put("file_name_incorrect_in_compilation_unit", "file name incorrect in compilation unit");
		errorDir.put("file_should_be_directory", "file should be directory");
		errorDir.put("final_cast_method", "final cast method");
		errorDir.put("formal_parameter_starting_with_lower_case_letter", "formal parameter starting with lower case letter");
		errorDir.put("function_returning_values_of_different_types", "function returning values of different types");
		errorDir.put("generic_parameter_expected", "generic parameter expected");
		errorDir.put("identifier_cannot_be_used_in_the_left_hand_side_of_an_assignment", "identifier cannot be used in the left hand side of an assignment");
		errorDir.put("identifier_expected_inside_method", "identifier expected inside method");
		errorDir.put("identifier_expected_outside_prototype", "identifier expected outside prototype"); 
		errorDir.put("identifier_was_not_declared", "identifier was not declared"); 
		errorDir.put("identifier_was_not_declared_inside_method", "identifier was not declared inside method");
		errorDir.put("identifier_was_not_declared_outside_method", "identifier was not declared outside method");
		errorDir.put("illegal_use_of_backquote", "illegal use of backquote");
		errorDir.put("incompatible_return_type_in_subprototype_method", "incompatible return type in subprototype method");
		errorDir.put("indexing_method_was_not_found_inside_method", "indexing method was not found inside method");
		errorDir.put("indexing_method_was_not_found_outside_method", "indexing method was not found outside method");
		errorDir.put("init_new_cannot_be_preceded_by_indexing_operator", "init new cannot be preceded by indexing operator");
		errorDir.put("init_new_methods_cannot_be_declared_in_interfaces", "init new methods cannot be declared in interfaces");
		errorDir.put("init_new_should_be_public", "init new should be public");
		errorDir.put("init_new_should_not_appear_in_grammar_method", "init new should not appear in grammar method");
		errorDir.put("init_should_not_be_abstract", "init should not be abstract");
		errorDir.put("init_should_not_be_declared_with_override", "init should not be declared with override");
		errorDir.put("init_should_not_be_final", "init should not be final");
		errorDir.put("init_should_return_Nil", "init should return Nil");
		errorDir.put("instance_variable_is_not_visible_here", "instance variable is not visible here");
		errorDir.put("interface_name_expected", "interface name expected");
		errorDir.put("internal_error", "internal error");
		errorDir.put("keyword_end_expected", "keyword end expected");
		errorDir.put("keyword_object_expected", "keyword object expected");
		errorDir.put("keyword_package_expected", "keyword package expected");
		errorDir.put("keyword_program_expected", "keyword program expected");
		errorDir.put("literal_string_expected", "literal string expected");
		errorDir.put("literal_string_expected_after_comma", "literal string expected after comma");
		errorDir.put("local_variable_is_being_redeclared", "local variable is being redeclared");
		errorDir.put("metaobject_attempt_to_add_two_methods_with_the_same_name_to_a_prototype", "metaobject attempt to add two methods with the same name to a prototype");
		errorDir.put("metaobject_call_error_missing_args_symbols", "metaobject call error missing args symbols");
		errorDir.put("metaobject_cannot_be_attached_to_prototype", "metaobject cannot be attached to prototype");
		errorDir.put("metaobject_error", "metaobject error");
		errorDir.put("metaobject_was_not_found_inside_method", "metaobject was not found inside method");
		errorDir.put("metaobject_was_not_found_outside_method", "metaobject was not found outside method");
		errorDir.put("metaobject_was_not_found_outside_prototype", "metaobject was not found outside prototype");
		errorDir.put("metaobject_wrong_number_of_parameters", "metaobject wrong number of parameters");
		errorDir.put("method_cannot_be_indexing_method", "method cannot be indexing method");
		errorDir.put("method_cannot_be_user_defined", "method cannot be user defined");
		errorDir.put("method_is_duplicated", "method is duplicated");
		errorDir.put("method_is_not_overriding_any_super_type_method", "method is not overriding any super type method");
		errorDir.put("method_is_not_visible_here", "method is not visible here");
		errorDir.put("method_is_overridden_method_with_different_visibility", "method is overridden method with different visibility");
		errorDir.put("method_should_be_declared_after_previous_method_with_the_same_selectors", "method should be declared after previous method with the same selectors");
		errorDir.put("method_was_not_found_in_current_prototype_or_super_prototypes", "method was not found in current prototype or super prototypes");
		errorDir.put("method_was_not_found_in_prototype_or_super_prototypes", "method was not found in prototype or super prototypes");
		errorDir.put("methods_with_the_same_selectors_and_different_return_types", "   	methods with the same selectors and different return types");   
		errorDir.put("methods_with_the_same_selectors_and_different_visibilities", "methods with the same selectors and different visibilities");
		errorDir.put("methods_with_the_same_selectors_with_and_without_final", "   	methods with the same selectors with and without final");   
		errorDir.put("methods_with_the_same_selectors_with_and_without_override", "methods with the same selectors with and without override");
		errorDir.put("mixing_of_different_parameter_kinds_in_generic_prototype", "mixing of different parameter kinds in generic prototype");
		errorDir.put("mixins_of_generic_and_non_generic_parameters", "mixins of generic and non generic parameters");
		errorDir.put("more_than_one_formal_plus_parameter_in_generic_prototype", "more than one formal plus parameter in generic prototype"); 
		errorDir.put("new_cannot_be_abstract", "new cannot be abstract");
		errorDir.put("new_cannot_be_declared_with_override", "new cannot be declared with override");
		errorDir.put("new_cannot_be_final", "new cannot be final");
		errorDir.put("new_methods_should_be_public", "new methods should be public");
		errorDir.put("new_with_illegal_return_type", "new with illegal return type");
		errorDir.put("no_public_protected_prototype_found_in_source_file", "no public protected prototype found in source file");
		errorDir.put("non_generic_prototype_in_the_same_source_file_with_generic_prototype", "non generic prototype in the same source file with generic prototype");
		errorDir.put("non_public_cast_method", "non public cast method");
		errorDir.put("non_public_generic_prototype", "non public generic prototype");
		errorDir.put("override_cannot_preced_method_signature_in_interfaces", "override cannot preced method signature in interfaces");
		errorDir.put("override_without_supertype", "override without supertype");
		errorDir.put("package_has_a_wrong_name", "package has a wrong name");
		errorDir.put("package_is_importing_itself", "package is importing itself");
		errorDir.put("package_name_expected", "package name expected");
		errorDir.put("package_was_not_found_inside_method", "package was not found inside method");
		errorDir.put("package_was_not_found_outside_prototype", "package was not found outside prototype");
		errorDir.put("parameter_is_being_redeclared", "parameter is being redeclared");
		errorDir.put("private_method_declared_with_abstract", "private method declared with abstract");
		errorDir.put("private_method_declared_with_final", "private method declared with final");
		errorDir.put("private_method_declared_with_override", "private method declared with override");
		errorDir.put("prototype_as_type_expected_inside_method", "prototype as type expected inside method");
		errorDir.put("prototype_cannot_be_used_in_the_left_hand_side_of_an_assignment", "prototype cannot be used in the left hand side of an assignment");
		errorDir.put("prototype_cannot_inherit_from_an_interface", "prototype cannot inherit from an interface");
		errorDir.put("prototype_imported_from_two_or_more_packages_inside_method", "prototype imported from two or more packages inside method");
		errorDir.put("prototype_was_not_found_inside_method", "prototype was not found inside method");
		errorDir.put("prototype_was_not_found_inside_prototyped", "prototype was not found inside prototyped");
		errorDir.put("prototype_was_not_found_outside_prototype", "prototype was not found outside prototype");
		errorDir.put("qualifier_cannot_preced_method_signature_in_interfaces", "qualifier cannot preced method signature in interfaces");
		errorDir.put("real_parameter_of_generic_prototype_expected", "real parameter of generic prototype expected");
		errorDir.put("return_with_caret_outside_a_function", "return with caret outside a function");
		errorDir.put("right_parenthesis_expected_in_mixin_declaration", "right parenthesis expected in mixin declaration");
		errorDir.put("right_square_bracket_expected_in_indexing_method", "right square bracket expected in indexing method"); 
		errorDir.put("semicolon_expected", "semicolon expected");
		errorDir.put("tuple_field_name_expected", "tuple field name expected");
		errorDir.put("two_or_more_backquotes_in_unary_chain", "two or more backquotes in unary chain");
		errorDir.put("two_or_more_generic_prototype_in_the_same_source_file", "two or more generic prototype in the same source file");
		errorDir.put("two_or_more_init_methods", "two or more init methods");
		errorDir.put("two_or_more_public_protected_prototype_found_in_source_file", "two or more public protected prototype found in source file");
		errorDir.put("type_error_return_value_type_is_not_a_subtype_of_the_function_return_type", "type error return value type is not a subtype of the function return type");
		errorDir.put("type_error_return_value_type_is_not_a_subtype_of_the_method_return_type", "type error return value type is not a subtype of the method return type");
		errorDir.put("type_error_type_of_right_hand_side_of_assignment_is_not_a_subtype_of_the_type_of_left_hand_side", "type error type of right hand side of assignment is not a subtype of the type of left hand side");
		errorDir.put("typeof_used_in_union", "typeof used in union");
		errorDir.put("unary_method_cannot_be_used_in_the_left_hand_side_of_an_assignment", "unary method cannot be used in the left hand side of an assignment");
		errorDir.put("use_of_super_without_a_super_prototype", "use of super without a super prototype");
		errorDir.put("variable_was_not_declared", "variable was not declared");

	}
	

	public List<String> returnError() {

		doErrorList();

		keys = new ArrayList<String>(errorDir.keySet());

		return keys;
	}
	
	public String returnPath(String Key) {

		doErrorList();

		if(Key !=null)
			path = errorDir.get(Key);

		return path;
	}
	
	public String returnerrorName(String Key)
	{
		doFolderList();

		path = errorDir.get(Key);

		return path;
	}

	@Override
	public int compareTo(ErrorList arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	


}
