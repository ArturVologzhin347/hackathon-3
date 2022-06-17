package com.ithub.hackathon3.repository

import com.ithub.hackathon3.model.Predmet
import org.springframework.data.jpa.repository.JpaRepository

interface PredmetRepository : JpaRepository<Predmet, Int>
