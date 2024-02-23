package com.kbtg.bootcamp.posttest.admin;

import com.kbtg.bootcamp.posttest.lottery.LotteryRequest;
import com.kbtg.bootcamp.posttest.lottery.LotteryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/admin")
@RestController
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @Operation(summary = "Create lottery")
    @PostMapping(value = "/lotteries")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Create lottery",   content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LotteryResponse.class))
            })
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    public LotteryResponse createAccount(
            @RequestBody @Valid LotteryRequest lotteryRequest
    ) {
        return this.adminService.createLottery(lotteryRequest);

    }
}
