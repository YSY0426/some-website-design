# some-website-design
may give u some insipration
this is a website that presents a new user with a form containing fields for
entering a user name and password (the latter should use an appropriate type of
input element). The form also contain a checkbox, initially unchecked,
labeled “Check to log in automatically in the future.” If the user checks the box and
successfully logs in (that is, the user name and password are both “CSisCoolStuff”),
then the user name and password is stored in two cookies (you may assume
that cookies are enabled on the client). After successfully logging in, the user
see a page that says “Logged In Successfully.” An unsuccessful attempt to log in
should return the user to a page with an error message followed by the original
log-in form, with the checkbox checked if it was checked on the previous form and
unchecked otherwise. On subsequent visits, the servlet should attempt to log the
user in using cookie information, if available. If this fails, the user should see the
original log-in form, without any error message. If it succeeds, the user should see
a “Welcome Back” page. Set the cookies to expire after six months.
