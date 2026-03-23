package com.example.home_domain.home.usecase



data class HomeUseCases(
    val completeHabitUseCase: CompleteHabitUseCase,
    //llama a la api y obtiene el listado
    val getHabitsForDateUseCase: GetHabitsForDateUseCase,
    val syncHabitUseCase: SyncHabitUseCase

)