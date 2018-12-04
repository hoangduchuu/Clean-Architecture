package grabteacher.com.mobileui.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import grabteacher.com.mobileui.di.ViewModelFactory
import grabteacher.com.presentation.model.BrowseProjectsViewModel
import kotlin.reflect.KClass


/**
 * Created by Huu Hoang on 02/12/2018
 */
@Module
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(BrowseProjectsViewModel::class)
    abstract fun  provideProjectsViewModel(viewModel: BrowseProjectsViewModel): ViewModel

    @Binds
    abstract fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)