package com.kbtg.bootcamp.posttest.lottery;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/lotteries")
@RestController
public class LotteryController {
    private final LotteryService lotteryService;

    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @Operation(summary = "list all lotteries")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "list all lotteries",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = LotteryResponse.class)))
                    })
    })
    @GetMapping(value = "")
    public TicketsNumberResponse getLotteries() {
        return this.lotteryService.getLotteries();
    }

}
