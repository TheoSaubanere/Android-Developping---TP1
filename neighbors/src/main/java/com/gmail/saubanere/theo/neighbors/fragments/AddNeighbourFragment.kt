package com.gmail.saubanere.theo.neighbors.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gmail.saubanere.theo.data.NeighborRepository
import com.gmail.saubanere.theo.neighbors.fragments.databinding.AddNeighborBinding
import com.gmail.saubanere.theo.neighbors.fragments.ui.main.NavigationListener
import com.gmail.saubanere.theo.neightbors.models.Neighbor

class AddNeighbourFragment : Fragment(), TextWatcher {
    private var _binding: AddNeighborBinding? = null
    private val binding get() = _binding!!
    private var image: Boolean = false
    private var nom: Boolean = false
    private var telephone: Boolean = false
    private var webste: Boolean = false
    private var adresse: Boolean = false
    private var aPropos: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddNeighborBinding.inflate(inflater, container, false)
        with(binding) {
            imageInput.addTextChangedListener(this@AddNeighbourFragment)
            nomInput.addTextChangedListener(this@AddNeighbourFragment)
            telephoneInput.addTextChangedListener(this@AddNeighbourFragment)
            websteInput.addTextChangedListener(this@AddNeighbourFragment)
            adresseInput.addTextChangedListener(this@AddNeighbourFragment)
            aProposInput.addTextChangedListener(this@AddNeighbourFragment)
            enregistrerButton.isEnabled = false
            enregistrerButton.setOnClickListener {
                NeighborRepository.getInstance().createNeighbour(
                    neighbor = Neighbor(
                        NeighborRepository.getInstance().getIdNewNeighbour(),
                        nomInput.text.toString(),
                        imageInput.text.toString(),
                        adresseInput.text.toString(),
                        telephoneInput.text.toString(),
                        aProposInput.text.toString(),
                        true,
                        websteInput.text.toString()
                    )
                )
                Toast.makeText(requireActivity(), R.string.voisin_enregistre, Toast.LENGTH_LONG)
                    .show()
                (activity as? NavigationListener)?.showFragment(ListNeighborsFragment())
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        with(binding) {
            if (URLUtil.isValidUrl(imageInput.text.toString()) && !imageInput.text.isNullOrEmpty()) {
                imageLabel.error = null
                image = true
                Glide.with(itemListAvatar.context)
                    .load(imageInput.text.toString())
                    .apply(RequestOptions.circleCropTransform())
                    .placeholder(R.drawable.ic_baseline_person_outline_24)
                    .error(R.drawable.ic_baseline_person_outline_24)
                    .skipMemoryCache(false)
                    .into(itemListAvatar)
            } else imageInput.error = getString(R.string.web_helper_text)

            if (nomInput.text.toString().isNotBlank()  && !nomInput.text.isNullOrEmpty()) {
                nomLabel.error = null
                nom = true
            } else nomInput.error = getString(R.string.nom_helper_text)

            if ((
                telephoneInput.text.toString()
                    .startsWith("06") || telephoneInput.text.toString().startsWith("07")
                ) && telephoneInput.text.toString().length == 10  && !telephoneInput.text.isNullOrEmpty()
            ) {
                telephoneLabel.error = null
                telephone = true
            } else telephoneInput.error = getString(R.string.telephone_helper_text)

            if (URLUtil.isValidUrl(websteInput.text.toString())  && !websteInput.text.isNullOrEmpty()) {
                websteLabel.error = null
                webste = true
            } else websteInput.error = getString(R.string.web_helper_text)

            if (Patterns.EMAIL_ADDRESS.matcher(adresseInput.text.toString()).matches()  && !adresseInput.text.isNullOrEmpty()) {
                adresseLabel.error = null
                adresse = true
            } else adresseInput.error = getString(R.string.mail_helper_text)

            if (aProposInput.text.toString().length <= 30  && !aProposInput.text.isNullOrEmpty()) {
                aProposLabel.error = null
                aPropos = true
            } else aProposInput.error = getString(R.string.a_Propos_helper_text)

            enregistrerButton.isEnabled = image && nom && telephone && webste && adresse && aPropos
        }
    }
}
