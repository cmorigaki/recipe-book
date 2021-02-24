package br.com.recipebook.inappupdate.domain

import br.com.recipebook.inappupdate.InAppUpdateResult

interface InAppUpdater {
    suspend operator fun invoke(): InAppUpdateResult
}
