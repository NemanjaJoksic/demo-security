
# Security lib
This is small library built on top of spring boot framework used for user authentication. It is built to secure RESTfull applications.  All classes that has Controller in their names will be treated as RestControllers and on every call of method from these classes authentication will be checked.

By default, 2 APIs are provided for handling user registration and obtaining access token.<br/>
* ``/register`` - POST method which receives user information in body of HTTP request. User has two properties, username and password.<br/>
* ``/login`` - POST method which in Authorization header receives username and password in format ``Authorization: Basic <credentials>`` where ``<cretentials>`` is is the base64 encoding of username and password joined by a single colon `:`. If provided username password pair is correct return, access token which then should be used for accessing other APIs.

All other APIs are accessible only if user is successfully authenticated.

There are few properties which should be used for application configurations: <br/>
* ``security.user.store.type`` - currently only available value is ``rbd`` and users are stored in relational database in table ``USER`` with 3 columns: ``USERNAME``, ``HASHED_PASSWORD``,  ``CREATION_IP_ADDRESS``.<br/>
* ``security.password.encoding.type`` - currently there are two available values: ``none`` and ``bcrypt``. By choosing ``none`` passwords will be store in raw format and it is not recommended.  Another option is ``bcrypt`` and then password will be encoded by ``BCRYPT`` algorithm and then stored.<br/>
* ``security.authentication.type`` - currently only available value is ``jwt``. In this case JSON web tokens will be used as access tokens. Authentication is done by providing Authorization header in every HTTP request in format  ``Authorization: Bearer <token>`` where ``<token>`` is access token obtained using ``/login`` API.<br/>
* ``security.authentication.jwt.secret-key`` - should be configured only if property ``security.authentication.type`` has value ``jwt``. It is key used for signing tokens.<br/>
* ``security.authentication.jwt.expiration-time`` - should be configured only if property ``security.authentication.type`` has value ``jwt``. It represents time how long token will be valid. Time is given in seconds.<br/>
