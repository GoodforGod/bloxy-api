package io.api.bloxy.core

import io.api.bloxy.model.dto.livepeer.*
import org.jetbrains.annotations.NotNull


/**
 * API for Livepeer contract monitoring
 * More information - https://bloxy.info/api_methods#livepeer
 *
 * @author GoodforGod
 * @since 05.03.2019
 */
internal interface ILivepeerApi {

    /**
     * Lists external functions call to bond function with specified to argument
     * @param address receiving bond
     * @param managerProxy default is 0x511bc4556d823ae99630ae8de28b9b80df90ea2e
     * @param roundManagerProxy default is 0x3984fc4ceeef1739135476f625d36d6c35c40dc3
     * @param limit max result (MAX 100100)
     * @param offset of the list from origin (0) (MAX 100000)
     */
    @NotNull
    fun bonds(
        address: String,
        managerProxy: String = "",
        roundManagerProxy: String = "",
        limit: Int = 1000,
        offset: Int = 0
    ): List<Bond>

    /**
     * Lists Bond/Rebond/Unbond events with optional filter by delegate/delegator argument
     * @param delegate default is 0xe9e284277648fcdb09b8efc1832c73c09b5ecf59
     * @param delegator address
     * @param managerProxy default is 0x511bc4556d823ae99630ae8de28b9b80df90ea2e
     * @param roundManagerProxy default is 0x3984fc4ceeef1739135476f625d36d6c35c40dc3
     * @param limit max result (MAX 100100)
     * @param offset of the list from origin (0) (MAX 100000)
     */
    @NotNull
    fun bondEvents(
        delegate: String = "",
        delegator: String = "",
        managerProxy: String = "",
        roundManagerProxy: String = "",
        limit: Int = 1000,
        offset: Int = 0
    ): List<Event>

    /**
     * Lists external functions call to unbond function
     * @param delegate default is 0xe9e284277648fcdb09b8efc1832c73c09b5ecf59
     * @param delegator address
     * @param managerProxy default is 0x511bc4556d823ae99630ae8de28b9b80df90ea2e
     * @param roundManagerProxy default is 0x3984fc4ceeef1739135476f625d36d6c35c40dc3
     * @param limit max result (MAX 100100)
     * @param offset of the list from origin (0) (MAX 100000)
     */
    @NotNull
    fun unbondEvents(
        delegate: String = "",
        delegator: String = "",
        managerProxy: String = "",
        roundManagerProxy: String = "",
        limit: Int = 1000,
        offset: Int = 0
    ): List<Event>

    /**
     * Lists rebond events with specified delegate/delegator argument
     * @param delegate default is 0xe9e284277648fcdb09b8efc1832c73c09b5ecf59
     * @param delegator address
     * @param managerProxy default is 0x511bc4556d823ae99630ae8de28b9b80df90ea2e
     * @param roundManagerProxy default is 0x3984fc4ceeef1739135476f625d36d6c35c40dc3
     * @param limit max result (MAX 100100)
     * @param offset of the list from origin (0) (MAX 100000)
     */
    @NotNull
    fun rebondEvents(
        delegate: String = "",
        delegator: String = "",
        managerProxy: String = "",
        roundManagerProxy: String = "",
        limit: Int = 1000,
        offset: Int = 0
    ): List<Event>

    /**
     * Lists token mint events resulted from reward call
     * @param address receiving bond
     * @param managerProxy default is 0x511bc4556d823ae99630ae8de28b9b80df90ea2e
     * @param roundManagerProxy default is 0x3984fc4ceeef1739135476f625d36d6c35c40dc3
     * @param limit max result (MAX 100100)
     * @param offset of the list from origin (0) (MAX 100000)
     */
    @NotNull
    fun rewards(
        address: String,
        managerProxy: String = "",
        roundManagerProxy: String = "",
        limit: Int = 1000,
        offset: Int = 0
    ): List<Reward>

    /**
     * Lists new rounds events
     * @param roundManagerProxy default is 0x3984fc4ceeef1739135476f625d36d6c35c40dc3
     * @param limit max result (MAX 100100)
     * @param offset of the list from origin (0) (MAX 100000)
     */
    @NotNull
    fun rounds(
        roundManagerProxy: String = "",
        limit: Int = 1000,
        offset: Int = 0
    ): List<Round>


    /**
     * Lists delegates with statistics
     * @param managerProxy default is 0x511bc4556d823ae99630ae8de28b9b80df90ea2e
     */
    @NotNull
    fun delegates(
        managerProxy: String = ""
    ): List<Delegate>

    /**
     * Lists delegators with statistics
     * @param managerProxy default is 0x511bc4556d823ae99630ae8de28b9b80df90ea2e
     */
    @NotNull
    fun delegators(
        managerProxy: String = ""
    ): List<Delegator>

    /**
     * Lists getDelegators smart contract metod results
     * @param managerProxy default is 0x511bc4556d823ae99630ae8de28b9b80df90ea2e
     * @param roundManagerProxy default is 0x3984fc4ceeef1739135476f625d36d6c35c40dc3
     */
    @NotNull
    fun delegatorStatus(
        managerProxy: String = "",
        roundManagerProxy: String = ""
    ): List<DelegatorStatus>
}