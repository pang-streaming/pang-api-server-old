package com.pangapiserver.presentation.donation;

import com.pangapiserver.application.donation.DonationUseCase;
import com.pangapiserver.application.donation.data.DonationRequest;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.presentation.donation.document.DonationDocuments;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/donation")
@RequiredArgsConstructor
public class DonationController implements DonationDocuments {
    private final DonationUseCase useCase;

    @Override
    @PostMapping
    public Response createDonation(@Valid @RequestBody DonationRequest request) {
        return useCase.createDonation(request);
    }
}
