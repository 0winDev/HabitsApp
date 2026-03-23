package com.example.authentication_domain.usecase

import com.example.authentication_domain.matcher.EmailMatcher

class ValidateEmailUseCase (private val emailMatcher: EmailMatcher){
    //recive email and return boolean
    operator fun invoke(email:String):Boolean{
        //this verify if the email match the pattern of a valid email address
        return emailMatcher.isValid(email)

    }
}