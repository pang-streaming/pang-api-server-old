package com.pangapiserver.presentation.donation;

import com.pangapiserver.application.donation.DonationUseCase;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.presentation.donation.document.DonationDocuments;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/donation")
@RequiredArgsConstructor
public class DonationController implements DonationDocuments {
    private final DonationUseCase useCase;

    @Override
    @PostMapping
    public Response createDonation() {
        return useCase.createDonation();
    }
}
