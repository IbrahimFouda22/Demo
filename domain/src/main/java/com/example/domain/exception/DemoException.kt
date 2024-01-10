package com.example.domain.exception

open class DemoException(error: String?): Exception(error)
open class AuthException(error: String?):DemoException(error)
open class ValidateException(error: String?):DemoException(error)
open class WrongDataException(error: String?):ValidateException(error)
open class EmptyDataException(error: String?):ValidateException(error)
class NoInternetException(error: String?):DemoException(error)
class ServerException(error: String?):DemoException(error)