package dev.rssreader.entity.network

import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class InternetConnectionListener @Inject constructor(val subject: BehaviorSubject<Unit>)