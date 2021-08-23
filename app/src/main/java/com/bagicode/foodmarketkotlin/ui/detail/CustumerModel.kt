package com.bagicode.foodmarketkotlin.ui.detail

import com.midtrans.sdk.corekit.core.Constants
import com.midtrans.sdk.corekit.core.LocalDataHandler
import com.midtrans.sdk.corekit.core.TransactionRequest
import com.midtrans.sdk.corekit.models.*
import com.midtrans.sdk.corekit.models.snap.Authentication
import com.midtrans.sdk.corekit.models.snap.CreditCard
import java.util.*

class CustumerModel {
    fun userDetails(
        nama: String?,
        email: String?,
        nohp: String?,
        address: String?,
        city: String?,
        codepos: String?,
        country: String?
    ) {
        var userDetail: UserDetail?
        userDetail = LocalDataHandler.readObject("user_details", UserDetail::class.java)
        if (userDetail == null) {
            userDetail = UserDetail()
            userDetail.userFullName = nama
            userDetail.email = email
            userDetail.phoneNumber = nohp
            userDetail.userId = UUID.randomUUID().toString()
            val userAddresses: MutableList<UserAddress> = ArrayList()
            val userAddress = UserAddress()
            userAddress.address = address
            userAddress.city = city
            userAddress.country = country
            userAddress.zipcode = codepos
            userAddress.addressType = Constants.ADDRESS_TYPE_BOTH
            userAddresses.add(userAddress)
            userDetail.userAddresses = ArrayList(userAddresses)
            LocalDataHandler.saveObject("user_details", userDetail)
        }
    }

    companion object {
        fun customerDetails(): CustomerDetails {
            val customerDetails = CustomerDetails()
            customerDetails.firstName = "Angga Risky"
            customerDetails.phone = "6282208199688"
            customerDetails.email = "wepanda@gmail.com"
            return customerDetails
        }

        @JvmStatic
        fun transactionRequest(
            id: String?,
            price: Int,
            qty: Int,
            name: String?
        ): TransactionRequest {
            val transactionRequest =
                TransactionRequest(System.currentTimeMillis().toString() + " ", price.toDouble())
            transactionRequest.customerDetails = customerDetails()
            val details = ItemDetails(id, price.toDouble(), qty, name)
            val itemDetails = ArrayList<ItemDetails>()
            itemDetails.add(details)
            transactionRequest.itemDetails = itemDetails
            val creditCard = CreditCard()
            creditCard.isSaveCard = false
            creditCard.authentication = Authentication.AUTH_RBA
            creditCard.bank = BankType.MANDIRI
            transactionRequest.creditCard = creditCard
            return transactionRequest
        }
    }
}