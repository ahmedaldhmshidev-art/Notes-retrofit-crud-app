Notes App

<div align="center"><img src="https://img.shields.io/badge/Kotlin-Android-purple?style=for-the-badge&logo=kotlin" />
<img src="https://img.shields.io/badge/Retrofit-REST%20API-009688?style=for-the-badge" />
<img src="https://img.shields.io/badge/Architecture-MVVM-blue?style=for-the-badge" /></div><div dir="rtl" align="right"><h2>نبذة عن التطبيق</h2>تطبيق Android لإدارة الملاحظات باستخدام Kotlin وREST API.

يتيح التطبيق للمستخدم عرض الملاحظات وإضافة ملاحظات جديدة وتعديلها وحذفها من خلال الاتصال بخادم خارجي.

يركز المشروع على التعامل مع الشبكة وتنفيذ عمليات CRUD باستخدام Retrofit مع تنظيم الكود وفق معمارية MVVM.

<h2>الميزات</h2>- جلب جميع الملاحظات باستخدام GET
- إضافة ملاحظة جديدة باستخدام POST
- تعديل ملاحظة موجودة باستخدام PUT
- حذف ملاحظة باستخدام DELETE
- تحديث البيانات بعد كل عملية ناجحة
- التعامل مع الأخطاء باستخدام Result
- عرض الملاحظات باستخدام RecyclerView
- استخدام معمارية MVVM

</div><div dir="ltr" align="left"><h2>Tech Stack</h2>Technology| Usage
Kotlin| Primary programming language
Retrofit| REST API requests
Kotlin Coroutines| Asynchronous operations
MVVM| Application architecture
RecyclerView| Notes list
Result| Error and operation result handling

</div><div dir="rtl" align="right"><h2>طريقة الاستخدام</h2>1. عند فتح التطبيق يتم جلب الملاحظات من الخادم.
2. يمكن إضافة ملاحظة جديدة.
3. يمكن تعديل الملاحظات الموجودة.
4. يمكن حذف أي ملاحظة.
5. يتم تحديث قائمة الملاحظات بعد كل عملية ناجحة.

</div><div dir="ltr" align="left"><h2>Architecture</h2>The application follows the MVVM architecture pattern.

Retrofit is used to communicate with the REST API, while Coroutines are used to handle asynchronous network operations.

The application supports CRUD operations for managing notes.

<h2>Getting Started</h2><h3>Requirements</h3>- Android Studio
- Android SDK
- Kotlin

<h3>Setup</h3>1. Clone the repository.
2. Open the project in Android Studio.
3. Sync the project with Gradle.
4. Build and run the application.

<h2>Project Status</h2>Completed personal Android project.

<h2>Author</h2>Ahmed Ali Aldhmshi

GitHub: "ahmedaldhmshidev-art" (https://github.com/ahmedaldhmshidev-art)

</div>