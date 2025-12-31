
import androidx.lifecycle.*
import com.example.retrofit_crud_just_internet.Model.Note
import com.example.retrofit_crud_just_internet.VmRepo.RepositoryNote
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class NoteViewModel(private val repo : RepositoryNote) : ViewModel() {

    private val _note = MutableLiveData<List<Note>>()
    val noteLive : LiveData<List<Note>> = _note

    private val _message = MutableLiveData<String>()
    val message : LiveData<String> = _message

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage : LiveData<String> = _errorMessage

    fun fetchNote()
    {
        viewModelScope.launch{
            try {
//                تنقل  withContext(Dispatchers.IO)  تنفيذ مابداخلها الي خيط مخصص i/o لتنفيذ العمليات الثقيلة مثل الوصول الي الشبكة او DB
                val fetch = withContext(Dispatchers.IO){
                repo.fetchAllNote()
            }
             if (fetch.isSuccessful)
             {
                 _note . postValue ( fetch . body() )
             } else
             {
                 _errorMessage.value = "فشل تحميل الملاحضات ${fetch.code()}"
             }

            } catch (e:Exception)
            {
            handException(e)
            }

        }
    }

    fun addNewNote(note: Note)
    {
        viewModelScope.launch {
        try
        {
         val add = withContext(Dispatchers.IO){
             repo.addNewNote(note)
         }
            if (add.isSuccessful)
            {
               val dateNew = add.body()
                dateNew ?. let {
                   val current = _note.value ?. toMutableList() ?: mutableListOf()
                   current.add(it)
                   _note.postValue(current)
                }
                _message.value = "تمت الاضافة بنجاح"
            }else
            {
                _errorMessage.value = "فشل اثنا الاضافة ${add.code()}"
            }
        }  catch (e:Exception)
        {
            handException(e)
        }
        }

    }
//    التعديل
    fun updateNote(id : String , note: Note){
        viewModelScope.launch {
            try
            {
            val up = withContext(Dispatchers.IO) {
                repo.updateNote(id , note)
            }
            if (up.isSuccessful)
            {
               val dataNew = up.body()
                dataNew ?. let {
                    val current = _note.value ?. toMutableList() ?: mutableListOf()
                    val index = current . indexOfFirst { n -> n.id == id }
                    if (index != -1 ){
                        current[index] = it
                        _note.postValue(current)
                    }
                }
                _message.value = "تم تعديل الملاحضة "
            }else
            {
                _errorMessage.value = "فشل في تعديل الملاحضة "
            }
            } catch (e:Exception)
            {
                handException(e)
            }
        }
    }
//    الحذف
    fun deletedNote( id : String)
    {
        viewModelScope.launch {
            try
            {
            val deleted = withContext(Dispatchers.IO)
            {
                repo.removedNote(id)
            }
                if (deleted.isSuccessful)
                {
                   val current = _note . value ?. toMutableSet() ?: mutableSetOf()
                   val d = current . filterNot { it.id == id  }
                    _note.postValue(d)
                    _message . value = "تم حذف الملاحضة "
                }else
                {
                    _errorMessage . value = " فشل في حذف الملاحضة ${deleted.code()} "
                }
            }catch (e:Exception)
            {
                handException(e)
            }
        }
    }
// دالة معالجة الأخطأ
    private fun handException(e: Exception)
    {
        when (e)
        {
            is IOException         -> _errorMessage.value = "خطأ في الأتصال بالانترنت"
            is HttpException       -> _errorMessage.value = "خطأ من السرفر ${e.code()}"
            is JsonSyntaxException -> _errorMessage.value = "خطأ في قرأة البيانات "
            else                   -> _errorMessage.value = "حدث خطأ غير معروف${e.message}"
        }
    }
}
// factory
class NoteViewModelFactory(
    private val repository: RepositoryNote
) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


