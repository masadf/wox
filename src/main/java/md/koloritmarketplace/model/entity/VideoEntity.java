package md.koloritmarketplace.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "videos", schema = "marketplace")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoEntity {
    @Id
    @Column(name = "videoid", nullable = false)
    @SequenceGenerator(name = "videoGenerator", sequenceName = "marketplace.video_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "videoGenerator")
    private Long videoId;

    @Column(name = "appid")
    private Long appId;

    @Column(name = "value")
    private String value;

}
