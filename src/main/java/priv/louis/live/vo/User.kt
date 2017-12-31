package priv.louis.live.vo

data class User(
        var id: String,
        var userName: String,
        var password: String,
        var gender: String?,
        var nick: String?,
        var registerDate: String?,
        var lastLoginDate: String?,
        var trueNameCert: String?,
        var trueName: String?,
        var phoneNumber: String?,
        var email: String?,
        var status: String
) {
}