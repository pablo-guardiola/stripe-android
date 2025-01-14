package com.stripe.android.paymentsheet.analytics

import androidx.annotation.Keep
import com.stripe.android.model.PaymentMethodCode
import com.stripe.android.paymentsheet.DeferredIntentConfirmationType
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.model.PaymentSelection

internal interface EventReporter {

    /**
     * PaymentSheet has been instantiated or FlowController has finished its configuration.
     */
    fun onInit(
        configuration: PaymentSheet.Configuration,
        isDeferred: Boolean,
    )

    /**
     * PaymentSheet or FlowController have started loading.
     */
    fun onLoadStarted()

    /**
     * PaymentSheet or FlowController have successfully loaded the information required to be
     * rendered.
     */
    fun onLoadSucceeded(
        linkEnabled: Boolean,
        currency: String?,
    )

    /**
     * PaymentSheet or FlowController have failed to load.
     */
    fun onLoadFailed(error: Throwable)

    /**
     * PaymentSheet or FlowController have failed to load from the Elements session endpoint.
     */
    fun onElementsSessionLoadFailed(error: Throwable)

    /**
     * PaymentSheet has been dismissed by pressing the close button.
     */
    fun onDismiss()

    /**
     * PaymentSheet is now being displayed and its first screen shows the customer's saved payment
     * methods.
     */
    fun onShowExistingPaymentOptions()

    /**
     * PaymentSheet is now being displayed and its first screen shows the payment method form.
     */
    fun onShowNewPaymentOptionForm()

    /**
     * The customer has selected one of the available payment methods in the payment method form.
     */
    fun onSelectPaymentMethod(
        code: PaymentMethodCode,
    )

    /**
     * The customer has selected one of their existing payment methods.
     */
    fun onSelectPaymentOption(
        paymentSelection: PaymentSelection,
    )

    /**
     * The customer has pressed the confirm button.
     */
    fun onPressConfirmButton()

    /**
     * Payment or setup have succeeded.
     */
    fun onPaymentSuccess(
        paymentSelection: PaymentSelection?,
        deferredIntentConfirmationType: DeferredIntentConfirmationType?,
    )

    /**
     * Payment or setup have failed.
     */
    fun onPaymentFailure(
        paymentSelection: PaymentSelection?,
        error: PaymentSheetConfirmationError,
    )

    /**
     * The client was unable to parse the response from LUXE.
     */
    fun onLpmSpecFailure()

    /**
     * The user has auto-filled a text field.
     */
    fun onAutofill(
        type: String,
    )

    enum class Mode(val code: String) {
        Complete("complete"),
        Custom("custom");

        @Keep
        override fun toString(): String = code
    }
}
